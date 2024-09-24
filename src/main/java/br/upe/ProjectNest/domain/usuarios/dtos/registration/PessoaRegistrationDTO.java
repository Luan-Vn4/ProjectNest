package br.upe.ProjectNest.domain.usuarios.dtos.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link br.upe.ProjectNest.domain.usuarios.models.Pessoa}
 */
public record PessoaRegistrationDTO(@NotNull String apelido, @NotNull @Email String email, @NotNull String senha,
                                    @NotNull String nome, @NotNull String sobrenome,
                                    String pronomes) implements UsuarioRegistrationDTO {
}