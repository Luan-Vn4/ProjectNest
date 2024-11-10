package br.upe.ProjectNest.domain.contribuicoes.services;

import br.upe.ProjectNest.domain.contribuicoes.models.Contribuicao;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoMapper;
import br.upe.ProjectNest.domain.contribuicoes.repositories.ContribuicaoRepository;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoMapper;
import br.upe.ProjectNest.domain.projetos.services.ProjetoService;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioMapper;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


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
        Contribuicao contribuicao = contribuicaoRepository.findById(id).orElse(null);

        if (contribuicao == null) {
            throw new RuntimeException("Não foi possivel encontrar uma contribuição com id: " + id);
        }

        return contribuicaoMapper.toDto(contribuicao);
    }

    @Override
    @Transactional
    public ContribuicaoDTO save(ContribuicaoCreationDTO contribuicaoDTO) {
        UsuarioDTO existingUsuario = usuarioService.getByUuid(contribuicaoDTO.idUsuario()).orElse(null);
        ProjetoDTO existingProjeto = projetoService.getById(contribuicaoDTO.idProjeto());

        if (existingUsuario == null) {
            throw new RuntimeException("Não foi possivel encontrar um usuário com id: " + contribuicaoDTO.idUsuario());
        }

        if (existingProjeto == null) {
            throw new RuntimeException("Não foi possivel encontrar um projeto com id: " + contribuicaoDTO.idProjeto());
        }

        Contribuicao contribuicao = contribuicaoMapper.toEntity(contribuicaoDTO);
        contribuicao.setUsuario(usuarioMapper.toEntity(existingUsuario));
        contribuicao.setProjeto(projetoMapper.toEntity(existingProjeto));

        return contribuicaoMapper.toDto(contribuicaoRepository.save(contribuicao));

    }

    @Override
    @Transactional
    public void update(ContribuicaoDTO contribuicaoDTO) {
        Contribuicao existingContribuicao = contribuicaoRepository.findById(contribuicaoDTO.uuid()).orElseThrow(() ->
                new RuntimeException("Não foi possivel encontrar uma contribuicao com id: " + contribuicaoDTO.uuid())
        );

        if (!existingContribuicao.getUsuario().getUuid().equals(contribuicaoDTO.idUsuario())) {
            UsuarioDTO novoUsuario = usuarioService.getByUuid(contribuicaoDTO.idUsuario())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + contribuicaoDTO.idUsuario()));
            existingContribuicao.setUsuario(usuarioMapper.toEntity(novoUsuario));
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

        if (existingContribuicao == null) {
            throw new RuntimeException("Não foi possivel encontrar uma contribuicao com id: " + id);
        }

        contribuicaoRepository.delete(contribuicaoMapper.toEntity(existingContribuicao));
    }
}
