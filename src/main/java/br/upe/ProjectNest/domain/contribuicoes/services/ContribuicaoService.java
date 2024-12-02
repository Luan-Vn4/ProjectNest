package br.upe.ProjectNest.domain.contribuicoes.services;


import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;

import java.util.List;
import java.util.UUID;


public interface ContribuicaoService {

    List<ContribuicaoDTO> getAll();

    ContribuicaoDTO getById(UUID id);

    ContribuicaoDTO save(ContribuicaoCreationDTO contribuicaoDTO);

    void update(ContribuicaoDTO contribuicaoDTO);

    void delete(UUID id);

    List<ContribuicaoDTO> findContribuicoesByProjeto(UUID id);

    List<UsuarioDTO> getContribuintes(UUID idContribuicao, List<UUID> idsContribuintes);
}
