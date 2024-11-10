package br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PasswordChangeDTO(
    @NotNull @Size(min=6)
    String oldPassword,
    @NotNull @Size(min=6)
    String newPassword
) {}
