package br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.dtos.TokenDTO;

import java.io.Serializable;

public record AuthResponseDTO(UsuarioDTO usuarioDTO, TokenDTO tokenDTO) implements Serializable {
}
