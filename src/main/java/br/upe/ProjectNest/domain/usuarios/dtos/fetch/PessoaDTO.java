package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @NotNull @Size(min = 60, max = 60)
    String senha,
    @NotNull @Size(max = 50)
    String nome,
    @NotNull @Size(max = 50)
    String sobrenome,
    @Size(max = 25)
    String pronomes) implements UsuarioDTO {}