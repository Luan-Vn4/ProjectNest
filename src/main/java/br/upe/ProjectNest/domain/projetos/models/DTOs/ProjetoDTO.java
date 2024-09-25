package br.upe.ProjectNest.domain.projetos.models.DTOs;

import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.projetos.models.enums.Escopo;
import br.upe.ProjectNest.domain.projetos.models.enums.Status;

import java.util.UUID;

public record ProjetoDTO(UUID idDono, String titulo, String descricao, String urlRepositorio, Escopo escopo,
                         Status status) {
    public static ProjetoDTO from(Projeto projeto) {
        return new ProjetoDTO(
                projeto.getDono().getUuid(),
                projeto.getTitulo(),
                projeto.getDescricao(),
                projeto.getUrlRepositorio(),
                projeto.getEscopo(),
                projeto.getStatus());
    }


}
