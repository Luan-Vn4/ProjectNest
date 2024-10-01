package br.upe.ProjectNest.domain.contribuicoes.models.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ContribuicaoCreationDTO(
        @NotNull
        UUID idUsuario,
        @NotNull
        UUID idProjeto,
        @NotNull
        @Size(max = 100)
        String titulo,
        @NotNull
        String descricao,
        @NotNull
        String urlRepositorio
) {
}
