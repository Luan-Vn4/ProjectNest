package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.dtos.TokenDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.InvalidTokenSubjectException;
import br.upe.ProjectNest.infrastructure.security.tokens.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    // ATRIBUTOS
    private UsuarioService usuarioService;

    private AuthenticationManager authManager;

    private TokenService tokenService;

    private UsuarioCreationMapper usuarioCreationMapper;


    // MÉTODOS
    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        var authCredentials = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password());
        authManager.authenticate(authCredentials);

        UsuarioDTO usuarioDTO = usuarioService.getByEmail(authDTO.email()).get();
        TokenDTO tokenDTO = tokenService.generateToken(usuarioDTO.uuid().toString());

        return new AuthResponseDTO(usuarioDTO, tokenDTO);
    }

    @Override
    public AuthResponseDTO validateToken(String token) {
        TokenDTO tokenDTO = tokenService.validateToken(TokenUtils.extractToken(token));
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getByUuid(UUID.fromString(tokenDTO.subject()));

        if (usuarioDTO.isEmpty()) throw new InvalidTokenSubjectException(token, tokenDTO.subject());

        return new AuthResponseDTO(usuarioDTO.get(), tokenDTO);
    }

    @Override
    public UsuarioDTO register(UsuarioCreationDTO dto) {
        Usuario usuario = usuarioCreationMapper.toEntity(dto);

        usuario.setSenha(encodePassword(usuario.getSenha()));

        UsuarioDTO usuarioDTO = usuarioService.create(usuarioCreationMapper.toDto(usuario));

        return usuarioDTO;
    }

    private String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public void changePassword(String token, UUID uuid, PasswordChangeDTO dto) {
        // TODO changePassword()
    }

    @Override
    public void deleteAccount(String token, UUID uuid, String password) {
        // TODO deleteAccount()
    }

}
