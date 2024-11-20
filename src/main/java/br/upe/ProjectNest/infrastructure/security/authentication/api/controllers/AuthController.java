package br.upe.ProjectNest.infrastructure.security.authentication.api.controllers;

import br.upe.ProjectNest.domain.usuarios.dtos.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.PessoaDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.EmpresaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.PessoaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AuthController {

    ResponseEntity<AuthResponseDTO> authenticate(@Valid AuthDTO dto);

    ResponseEntity<AuthResponseDTO> validateToken(String token);

    ResponseEntity<PessoaDTO> register(@Valid PessoaCreationDTO dto);

    ResponseEntity<EmpresaDTO> register(@Valid EmpresaCreationDTO dto);

    ResponseEntity<?> changePassword(String authorization, UUID uuid, @Valid PasswordChangeDTO dto);

    ResponseEntity<?> deleteAccount(String authorization, @Size(min=6) String password, UUID uuid);

}
