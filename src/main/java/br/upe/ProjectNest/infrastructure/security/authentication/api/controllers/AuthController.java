package br.upe.ProjectNest.infrastructure.security.authentication.api.controllers;

import br.upe.ProjectNest.domain.usuarios.dtos.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.PessoaDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.EmpresaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.PessoaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AuthController {

    ResponseEntity<AuthResponseDTO> authenticate(AuthDTO dto);

    ResponseEntity<AuthResponseDTO> validateToken(String token);

    ResponseEntity<PessoaDTO> register(PessoaCreationDTO dto);

    ResponseEntity<EmpresaDTO> register(EmpresaCreationDTO dto);

    ResponseEntity<Void> changePassword(String token, UUID uuid, PasswordChangeDTO dto);

    ResponseEntity<Void> deleteAccount(String token, String password, UUID uuid);

}
