package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.repositories.ProjetoRepository;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.fetch.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private ProjetoRepository projetoRepository;

    private UsuarioService usuarioService;
    private UsuarioMapper usuarioMapper;

    @Override
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto getById(UUID id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);

        if (projeto.isEmpty()) {
            throw new RuntimeException("Não foi encontrado nenhum projeto com id: " + id);
        }

        return projeto.get();
    }

    @Override
    @Transactional
    public UUID save(ProjetoDTO projetoDTO) {

        Optional<UsuarioDTO> donoOptional = usuarioService.getByUuid(projetoDTO.idDono());
        if (donoOptional.isEmpty()) {
            throw new RuntimeException("Dono do projeto não encontrado. Id: " + projetoDTO.idDono());
        }

        Projeto projeto = new Projeto();
        UsuarioDTO donoDTO = donoOptional.get();
        Usuario dono = usuarioMapper.toEntity(donoDTO);
        BeanUtils.copyProperties(projetoDTO, projeto);
        projeto.setDono(dono);

        Projeto projetoSalvo = projetoRepository.save(projeto);
        return projetoSalvo.getUuid();
    }

    @Override
    @Transactional
    public Projeto update(Projeto projeto) {
        getById(projeto.getUuid());

        return projetoRepository.save(projeto);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Projeto ProjetoExistente = getById(id);


        projetoRepository.delete(ProjetoExistente);

    }
}
