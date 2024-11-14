package br.upe.ProjectNest.domain.usuarios.dtos.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Empresa}
 */
public record EmpresaCreationDTO(
    @NotNull @Size(max = 50)
    String apelido,
    @NotNull @Size(max = 255) @Email
    String email,
    @NotNull
    String senha,
    @NotNull @Size(max = 14)
    String cnpj) implements UsuarioCreationDTO {}