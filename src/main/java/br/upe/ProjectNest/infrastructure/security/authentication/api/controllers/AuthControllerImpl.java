package br.upe.ProjectNest.infrastructure.security.authentication.api.controllers;

import br.upe.ProjectNest.domain.usuarios.dtos.EmpresaDTO;
import br.upe.ProjectNest.domain.usuarios.dtos.PessoaDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.EmpresaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.PessoaCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.services.AuthServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthControllerImpl implements AuthController {

    private AuthServiceImpl authServiceImpl;

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> authenticate(@Valid @RequestBody AuthDTO authDTO) {
        return ResponseEntity.ok(authServiceImpl.authenticate(authDTO));
    }

    @Override
    @PostMapping(value="/validate", headers=AUTHORIZATION)
    public ResponseEntity<AuthResponseDTO> validateToken(@RequestHeader(AUTHORIZATION) String token) {
        return ResponseEntity.ok(authServiceImpl.validateToken(token));
    }

    @Override
    @PostMapping("/usuarios/pessoas")
    public ResponseEntity<PessoaDTO> register(@Valid @RequestBody PessoaCreationDTO dto) {
        return ResponseEntity.ok((PessoaDTO) authServiceImpl.register(dto));
    }

    @Override
    @PostMapping("/usuarios/empresas")
    public ResponseEntity<EmpresaDTO> register(@Valid @RequestBody EmpresaCreationDTO dto) {
        return ResponseEntity.ok((EmpresaDTO) authServiceImpl.register(dto));
    }

    @Override
    @PatchMapping(value="/usuarios/{uuid}/password", headers=AUTHORIZATION)
    public ResponseEntity<?> changePassword(@RequestHeader(AUTHORIZATION) String authorization,
                                               @PathVariable UUID uuid,
                                               @Valid @RequestBody PasswordChangeDTO dto) {
        authServiceImpl.changePassword(authorization, uuid, dto);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping(value="/usuarios/{uuid}", headers=AUTHORIZATION)
    public ResponseEntity<?> deleteAccount(@RequestHeader(AUTHORIZATION) String authorization,
                                           @RequestBody @Size(min=6) String password,
                                           @PathVariable UUID uuid) {
        authServiceImpl.deleteAccount(authorization, uuid, password);
        return ResponseEntity.ok().build();
    }

}
