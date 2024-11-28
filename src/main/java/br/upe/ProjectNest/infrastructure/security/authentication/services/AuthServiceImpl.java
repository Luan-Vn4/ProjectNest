package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.exceptions.UsuarioNotFoundException;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import br.upe.ProjectNest.domain.usuarios.services.UsuarioService;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationMapper;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.dtos.TokenDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.InvalidTokenSubjectException;
import br.upe.ProjectNest.infrastructure.security.tokens.services.TokenService;
import br.upe.ProjectNest.infrastructure.security.tokens.utils.TokenUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service("authService")
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    // ATRIBUTOS
    private UsuarioService usuarioService;

    private UsuarioRepository usuarioRepository;

    private AuthenticationManager authManager;

    private TokenService tokenService;

    private UsuarioCreationMapper usuarioCreationMapper;

    private PasswordEncoder encoder;


    // MÉTODOS
    @Override
    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public AuthResponseDTO authenticate(AuthDTO authDTO) {
        var authCredentials = new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.senha());
        authManager.authenticate(authCredentials);

        UsuarioDTO usuarioDTO = usuarioService.getByEmail(authDTO.email()).get();
        TokenDTO tokenDTO = tokenService.generateToken(usuarioDTO.uuid().toString());

        return new AuthResponseDTO(usuarioDTO, tokenDTO);
    }

    @Override
    public AuthResponseDTO validateToken(String authorization) {
        TokenDTO tokenDTO = tokenService.validateToken(TokenUtils.extractToken(authorization));
        Optional<UsuarioDTO> usuarioDTO = usuarioService.getByUuid(UUID.fromString(tokenDTO.subject()));

        if (usuarioDTO.isEmpty()) throw new InvalidTokenSubjectException(authorization, tokenDTO.subject());

        return new AuthResponseDTO(usuarioDTO.get(), tokenDTO);
    }

    @Override
    public UsuarioDTO register(UsuarioCreationDTO dto) {
        Usuario usuario = usuarioCreationMapper.toEntity(dto);

        usuario.setSenha(encoder.encode(usuario.getSenha()));

        return usuarioService.create(usuarioCreationMapper.toDto(usuario));
    }

    @Override
    @Transactional
    @PreAuthorize("@authService.validateToken(#authorization).usuarioDTO().uuid() eq #uuid")
    public void changePassword(String authorization, UUID uuid, PasswordChangeDTO dto) {
        Optional<Usuario> usuario = usuarioRepository.findById(uuid);

        if (usuario.isEmpty()) throw new UsuarioNotFoundException("Não foi possível encontrar o " +
            "usuário com UUID: " + uuid);

        if (!encoder.matches(dto.oldPassword(), usuario.get().getSenha())) throw new
            AuthorizationDeniedException("A senha antiga fornecida não corresponde " +
            "à senha atual", new AuthorizationDecision(false));

        usuario.get().setSenha(encoder.encode(dto.newPassword()));
        usuarioRepository.save(usuario.get());
    }

    @Override
    @PreAuthorize("""
        hasRole(@roleNames.ADMIN) or
        @authService.validateToken(#authorization).usuarioDTO().uuid() eq #uuid
    """)
    public void deleteAccount(String authorization, UUID uuid, String password) {
        usuarioService.delete(uuid);
    }

}
