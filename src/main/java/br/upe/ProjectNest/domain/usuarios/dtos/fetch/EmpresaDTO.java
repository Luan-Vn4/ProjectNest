package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Empresa}
 */
public record EmpresaDTO (
  @NotNull
  UUID uuid,
  @NotNull @Size(max = 50)
  String apelido,
  @NotNull @Size(max = 255) @Email
  String email,
  @NotNull
  String senha,
  @NotNull @Size(max = 14)
  String cnpj) implements UsuarioDTO {}