package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoCreationDTO;
import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;

import java.util.List;
import java.util.UUID;

public interface ProjetoService {

    public List<ProjetoDTO> getAll();

    public ProjetoDTO getById(UUID id);

    public ProjetoDTO save(ProjetoCreationDTO projetoDTO);

    public void update(ProjetoDTO projetoDTO);

    public void delete(UUID id);
}
