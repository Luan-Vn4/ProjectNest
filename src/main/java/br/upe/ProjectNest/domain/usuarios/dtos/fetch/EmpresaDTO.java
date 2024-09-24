package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Empresa}
 */
public record EmpresaDTO(UUID uuid, @NotNull String apelido, @NotNull @Email String email, @NotNull String senha,
                         @NotNull String cnpj) implements UsuarioDTO {
}