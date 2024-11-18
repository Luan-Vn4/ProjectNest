package br.upe.ProjectNest.domain.contribuicoes.services;

import br.upe.ProjectNest.domain.contribuicoes.exceptions.ContribuicaoExistsException;
import br.upe.ProjectNest.domain.contribuicoes.exceptions.ContribuicaoNotFoundException;
import br.upe.ProjectNest.domain.contribuicoes.models.Contribuicao;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoMapper;
import br.upe.ProjectNest.domain.contribuicoes.repositories.ContribuicaoRepository;
import br.upe.ProjectNest.domain.projetos.exceptions.ProjetoNotFoundException;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoMapper;
import br.upe.ProjectNest.domain.projetos.services.ProjetoService;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ContribuicaoServiceImpl implements ContribuicaoService {

    private final ContribuicaoRepository contribuicaoRepository;
    private final UsuarioService usuarioService;
    private final ProjetoService projetoService;

    private final ContribuicaoMapper contribuicaoMapper;
    private final UsuarioMapper usuarioMapper;
    private final ProjetoMapper projetoMapper;

    @Override
    public List<ContribuicaoDTO> getAll() {
        return contribuicaoRepository.findAll().stream().map(contribuicaoMapper::toDto).toList();
    }

    @Override
    public ContribuicaoDTO getById(UUID id) {
        Contribuicao contribuicao = contribuicaoRepository.findById(id).orElseThrow(() -> new ContribuicaoNotFoundException(id));
        return contribuicaoMapper.toDto(contribuicao);
    }

    @Override
    @Transactional
    public ContribuicaoDTO save(ContribuicaoCreationDTO contribuicaoDTO) {
        Set<Usuario> existingUsuarios = usuarioService.findUsuariosByUUIDs(contribuicaoDTO.idUsuarios())
                .stream().map(usuarioMapper::toEntity).collect(Collectors.toSet());

        if (contribuicaoDTO.idUsuarios().size() != existingUsuarios.size())
            throw new RuntimeException("Há ao menos um usuário com id inválido.");

        if (existingUsuarios.isEmpty() || contribuicaoDTO.idUsuarios().isEmpty())
            throw new RuntimeException("É necessário existir ao menos um usuário");

        ProjetoDTO existingProjeto = projetoService.getById(contribuicaoDTO.idProjeto());

        Optional<Contribuicao> existingContribuicao = contribuicaoRepository.findByUrlRepositorio(contribuicaoDTO.urlRepositorio());

        if (!existingContribuicao.isEmpty())
            throw new ContribuicaoExistsException("url", contribuicaoDTO.urlRepositorio());

        if (existingProjeto == null) {
            throw new ProjetoNotFoundException(contribuicaoDTO.idProjeto());
        }

        Contribuicao contribuicao = contribuicaoMapper.toEntity(contribuicaoDTO);
        contribuicao.addUsuarios(existingUsuarios);
        contribuicao.setProjeto(projetoMapper.toEntity(existingProjeto));

        return contribuicaoMapper.toDto(contribuicaoRepository.save(contribuicao));

    }

    @Override
    @Transactional
    public void update(ContribuicaoDTO contribuicaoDTO) {
        Contribuicao existingContribuicao = contribuicaoRepository.findById(contribuicaoDTO.uuid()).orElseThrow(() ->
                new ContribuicaoNotFoundException(contribuicaoDTO.uuid()));

        if (!existingContribuicao.getUuid().equals(contribuicaoDTO.idUsuarios())) {
            Set<UsuarioDTO> novosUsuarios = usuarioService.findUsuariosByUUIDs(contribuicaoDTO.idUsuarios());
            existingContribuicao.addUsuarios(novosUsuarios.stream().map(usuarioMapper::toEntity).collect(Collectors.toSet()));
        }

        if (!existingContribuicao.getProjeto().getUuid().equals(contribuicaoDTO.idProjeto())) {
            ProjetoDTO novoProjeto = projetoService.getById(contribuicaoDTO.idProjeto());
            existingContribuicao.setProjeto(projetoMapper.toEntity(novoProjeto));
        }

        existingContribuicao.setTitulo(contribuicaoDTO.titulo());
        existingContribuicao.setDescricao(contribuicaoDTO.descricao());
        existingContribuicao.setUrlRepositorio(contribuicaoDTO.urlRepositorio());

        contribuicaoRepository.save(existingContribuicao);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        ContribuicaoDTO existingContribuicao = getById(id);

        contribuicaoRepository.delete(contribuicaoMapper.toEntity(existingContribuicao));
    }

}
