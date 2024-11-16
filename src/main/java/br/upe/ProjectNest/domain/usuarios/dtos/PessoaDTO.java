package br.upe.ProjectNest.domain.usuarios.dtos;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Pessoa}
 */
public record PessoaDTO(
    @NotNull
    UUID uuid,
    @NotNull @Size(max = 50)
    String apelido,
    @NotNull @Size(max = 255) @Email
    String email,
    @NotNull @Size(max = 50)
    String nome,
    @NotNull @Size(max = 50)
    String sobrenome,
    @NotNull
    Set<Role> roles,
    @Size(max = 25)
    String pronomes) implements UsuarioDTO {

    @Override
    @JsonProperty
    public UsuarioType type() {
        return UsuarioType.PESSOA;
    }

}