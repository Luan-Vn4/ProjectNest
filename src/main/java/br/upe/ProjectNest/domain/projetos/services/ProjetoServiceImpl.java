package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoMapper;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.models.enums.Escopo;
import br.upe.ProjectNest.domain.projetos.repositories.ProjetoRepository;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return projetoRepository.findById(id).map(projetoMapper::toDto).orElse(null);
    }

    @Override
    public ProjetoDTO save(ProjetoCreationDTO projetoDTO) {

        Optional<UsuarioDTO> donoOpt = usuarioService.getByUuid(projetoDTO.idDono());

        if (donoOpt.isEmpty()) {
            throw new RuntimeException("Não foi possivel encontrar um usuário com id: " + projetoDTO.idDono());
        }

        UsuarioDTO dono = donoOpt.get();

        Projeto projeto = projetoMapper.toEntity(projetoDTO);
        projeto.setDono(usuarioMapper.toEntity(dono));
        Projeto registeredProjeto = projetoRepository.save(projeto);

        return projetoMapper.toDto(registeredProjeto);

    }

    @Override
    public void update(ProjetoDTO projetoDTO) {
        Optional<Projeto> existingProjeto = projetoRepository.findById(projetoDTO.uuid());

        if (existingProjeto.isEmpty()) {
            throw new RuntimeException("Não foi possivel encontrar um projeto com id: " + projetoDTO.uuid());
        }
        Projeto projeto = existingProjeto.get();

        BeanUtils.copyProperties(projetoDTO, projeto);

        projetoRepository.save(projeto);
    }

    @Override
    public void delete(UUID id) {
        ProjetoDTO existingProjeto = getById(id);

        if (existingProjeto == null) {
            throw new RuntimeException("Não foi encontrado nenhum projeto com id: " + id);
        }

        projetoRepository.delete(projetoMapper.toEntity(existingProjeto));

    }
}
