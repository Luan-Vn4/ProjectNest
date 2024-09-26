package br.upe.ProjectNest.domain.usuarios.dtos.fetch;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioType;
import java.util.UUID;

public interface UsuarioDTO {

    UsuarioType type();

    UUID uuid();

    String apelido();

    String email();

}
