package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.exceptions.ProjetoExistsException;
import br.upe.ProjectNest.domain.projetos.exceptions.ProjetoNotFoundException;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoMapper;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.repositories.ProjetoRepository;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private ProjetoRepository projetoRepository;

    private final UsuarioService usuarioService;
    private final ProjetoMapper projetoMapper;
    private final UsuarioMapper usuarioMapper;


    @Override
    public List<ProjetoDTO> getAll() {
        return projetoRepository.findAll().stream().map(projetoMapper::toDto).toList();
    }

    @Override
    public ProjetoDTO getById(UUID id) {
        return projetoRepository.findById(id).map(projetoMapper::toDto).orElseThrow(() -> new ProjetoNotFoundException(id));
    }

    @Override
    public ProjetoDTO save(ProjetoCreationDTO projetoDTO) {

        Optional<UsuarioDTO> donoOpt = usuarioService.getByUuid(projetoDTO.idDono());

        if (donoOpt.isEmpty()) {
            throw new RuntimeException("Não foi possivel encontrar um usuário com id: " + projetoDTO.idDono());
        }

        Optional<Projeto> existingProjeto = projetoRepository.findByUrlRepositorio(projetoDTO.urlRepositorio());

        if (!existingProjeto.isEmpty()) throw new ProjetoExistsException("url", projetoDTO.urlRepositorio());

        UsuarioDTO dono = donoOpt.get();

        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto.setDono(usuarioMapper.toEntity(dono));
        Projeto registeredProjeto = projetoRepository.save(projeto);

        return projetoMapper.toDto(registeredProjeto);

    }

    @Override
    @Transactional
    public void update(ProjetoDTO projetoDTO) {
        Projeto existingProjeto = projetoRepository.findById(projetoDTO.uuid()).orElseThrow(() ->
                new ProjetoNotFoundException(projetoDTO.uuid())
        );

        if (!existingProjeto.getDono().getUuid().equals(projetoDTO.idDono())) {
            UsuarioDTO novoUsuario = usuarioService.getByUuid(projetoDTO.idDono())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + projetoDTO.idDono()));
            existingProjeto.setDono(usuarioMapper.toEntity(novoUsuario));
        }

        existingProjeto.setTitulo(projetoDTO.titulo());
        existingProjeto.setDescricao(projetoDTO.descricao());
        existingProjeto.setUrlRepositorio(projetoDTO.urlRepositorio());
        existingProjeto.setEscopo(projetoDTO.escopo());
        existingProjeto.setStatus(projetoDTO.status());

        projetoRepository.save(existingProjeto);
    }


    @Override
    public void delete(UUID id) {
        ProjetoDTO existingProjeto = getById(id);

        if (existingProjeto == null) {
            throw new ProjetoNotFoundException(id);
        }

        projetoRepository.delete(projetoMapper.toEntity(existingProjeto));

    }
}
