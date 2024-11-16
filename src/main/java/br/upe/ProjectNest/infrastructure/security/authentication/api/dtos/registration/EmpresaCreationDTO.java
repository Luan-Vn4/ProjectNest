package br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Empresa}
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public record EmpresaCreationDTO(
    @NotNull @Size(max = 50)
    String apelido,
    @NotNull @Size(max = 255) @Email
    String email,
    @NotNull @Size(min = 6)
    String senha,
    @NotNull @Size(max = 14) @CNPJ
    String cnpj) implements UsuarioCreationDTO {}