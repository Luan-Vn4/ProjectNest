package br.upe.ProjectNest.domain.contribuicoes.services;


import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;

import java.util.List;
import java.util.UUID;


public interface ContribuicaoService {

    List<ContribuicaoDTO> getAll();

    ContribuicaoDTO getById(UUID id);

    ContribuicaoDTO save(ContribuicaoCreationDTO contribuicaoDTO);

    void update(ContribuicaoDTO contribuicaoDTO);

    void delete(UUID id);

    List<ContribuicaoDTO> findContribuicoesByProjeto(UUID id);
}
