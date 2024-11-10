package br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthDTO(
    @NotNull @Email @Size(max=255)
    String email,
    @NotNull @Size(min = 6)
    String password
) {}
