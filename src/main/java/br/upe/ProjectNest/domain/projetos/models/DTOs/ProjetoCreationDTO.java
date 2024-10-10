package br.upe.ProjectNest.domain.projetos.models.DTOs;

import br.upe.ProjectNest.domain.projetos.models.enums.Escopo;
import br.upe.ProjectNest.domain.projetos.models.enums.Status;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record ProjetoCreationDTO(
        @NotNull
        UUID idDono,

        @NotNull @Size(max = 100)
        String titulo,

        @NotNull
        String descricao,

        @Size(max = 255)
        String urlRepositorio,

        @NotNull
        Escopo escopo,

        @NotNull
        Status status) {
}
