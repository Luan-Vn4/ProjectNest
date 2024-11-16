package br.upe.ProjectNest.domain.usuarios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Set;
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
    @NotNull @CNPJ
    String cnpj,
    @NotNull
    Set<String> roles) implements UsuarioDTO {

  @Override
  @JsonProperty
  public UsuarioType type() {
    return UsuarioType.EMPRESA;
  }

}