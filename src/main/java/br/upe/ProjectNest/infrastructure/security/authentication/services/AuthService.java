package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import java.util.UUID;

public interface AuthService {

    AuthResponseDTO authenticate(AuthDTO authDTO);

    AuthResponseDTO validateToken(String token);

    UsuarioDTO register(UsuarioCreationDTO dto);

    void changePassword(String token, UUID uuid, PasswordChangeDTO dto);

    void deleteAccount(String token, UUID uuid, String password);

}
