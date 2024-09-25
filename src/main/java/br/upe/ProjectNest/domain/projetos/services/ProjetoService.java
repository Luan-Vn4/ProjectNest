package br.upe.ProjectNest.domain.projetos.services;

import br.upe.ProjectNest.domain.projetos.models.DTOs.ProjetoDTO;
import br.upe.ProjectNest.domain.projetos.models.Projeto;

import java.util.List;
import java.util.UUID;

public interface ProjetoService {

    public List<Projeto> getAll();

    public Projeto getById(UUID id);

    public UUID save(ProjetoDTO projetoDTO);

    public Projeto update(Projeto projeto);

    public void delete(UUID id);
}
