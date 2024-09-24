package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * DTO for {@link Pessoa}
 */
public record PessoaDTO(UUID uuid, @NotNull String apelido, @NotNull @Email String email, @NotNull String senha,
                        @NotNull String nome, @NotNull String sobrenome, String pronomes) implements UsuarioDTO {
}