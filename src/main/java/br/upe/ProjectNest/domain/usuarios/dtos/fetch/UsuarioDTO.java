package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import java.util.UUID;

public interface UsuarioDTO {

    UUID uuid();

    String apelido();

    String email();

    String senha();

}
