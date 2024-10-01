package br.upe.ProjectNest.domain.contribuicoes.services;


import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoCreationDTO;
import br.upe.ProjectNest.domain.contribuicoes.models.DTOs.ContribuicaoDTO;

import java.util.List;
import java.util.UUID;

public interface ContribuicaoService {

    public List<ContribuicaoDTO> getAll();

    public ContribuicaoDTO getById(UUID id);

    public ContribuicaoDTO save(ContribuicaoCreationDTO contribuicaoDTO);

    public void update(ContribuicaoDTO contribuicaoDTO);

    public void delete(UUID id);

}
