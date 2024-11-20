package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;


import java.util.List;
import java.util.UUID;

public interface ProjetoService {

    List<ProjetoDTO> getAll();

    ProjetoDTO getById(UUID id);

    ProjetoDTO save(ProjetoCreationDTO projetoDTO);

    void update(ProjetoDTO projeto);

    void delete(UUID id);
}
