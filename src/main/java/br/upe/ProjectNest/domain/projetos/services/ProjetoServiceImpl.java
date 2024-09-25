package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.repositories.ProjetoRepository;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {
    private ProjetoRepository projetoRepository;

    private UsuarioService usuarioService;


    @Override
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto getById(UUID id) {
        return projetoRepository.findById(id).orElse(null);
    }

    @Override
    public UUID save(ProjetoDTO projetoDTO) {
//
//        Usuario dono = usuarioService.getByUuid(projetoDTO.idDono());
//        Projeto projeto = new Projeto();
//        BeanUtils.copyProperties(projetoDTO, projeto);
//        projeto.setDono(dono);
//        System.out.println(projeto);
        return null;
    }

    @Override
    public void update(ProjetoDTO projetoDTO) {

    }

    @Override
    public void delete(UUID id) {
        Projeto existingProjeto = getById(id);

        if (existingProjeto == null) {
            throw new RuntimeException("Não foi encontrado nenhum projeto com id: " + id);
        }

        projetoRepository.delete(existingProjeto);

    }
}
