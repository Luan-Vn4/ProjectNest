package br.upe.ProjectNest.domain.contribuicoes.models.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ContribuicaoDTO(
        @NotNull
        UUID uuid,

        @NotNull
        UUID idUsuario,

        @NotNull
        UUID idProjeto,

        @NotNull @Size(max = 100)
        String titulo,

        @NotNull
        String descricao,

        @Size(max = 255)
        String urlRepositorio
) {
}
