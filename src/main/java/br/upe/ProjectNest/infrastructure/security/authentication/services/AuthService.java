package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.dtos.UsuarioDTO;
import br.upe.ProjectNest.domain.usuarios.exceptions.UsuarioNotFoundException;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.login.AuthResponseDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.registration.UsuarioCreationDTO;
import br.upe.ProjectNest.infrastructure.security.authentication.api.dtos.update.PasswordChangeDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.InvalidTokenException;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.InvalidTokenSubjectException;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.ExpiredTokenException;
import jakarta.validation.constraints.Size;
import org.springframework.security.authorization.AuthorizationDeniedException;
import java.util.UUID;

public interface AuthService {

    AuthResponseDTO authenticate(AuthDTO authDTO);

    /**
     * Valida o token passado em authorization
     * @param authorization header de authorization com o token
     * @return {@link AuthResponseDTO} com dados do usuário autenticado e seu token
     * @throws InvalidTokenSubjectException caso não seja encontrado o usuário ao qual aquele token pertence
     * @throws ExpiredTokenException caso o token esteja expirado
     * @throws InvalidTokenException caso o token seja inválido
     */
    AuthResponseDTO validateToken(String authorization);

    UsuarioDTO register(UsuarioCreationDTO dto);

    /**
     * Muda a senha do usuário para uma nova
     * @param authorization header de authorization que contém o token
     * @param uuid {@link UUID} do usuário que está tendo sua senha alterada
     * @param dto {@link PasswordChangeDTO} com informações das senhas antiga e nova
     * @throws UsuarioNotFoundException caso o uuid especificado não corresponda a nenhum
     *                                  usuário existente
     * @throws AuthorizationDeniedException caso a senha atual fornecida não corresponda à senha verdadeira
     */
    void changePassword(String authorization, UUID uuid, PasswordChangeDTO dto);

    /**
     * Deleta uma conta de usuário do sistema
     * @param authorization header de authorization que contém o token
     * @param uuid {@link UUID} do usuário que será deletado do sistema
     * @param password senha atual do usuário que será deletado
     * @throws UsuarioNotFoundException caso o uuid especificado não corresponda a nenhum
     *                                  usuário existente
     */
    void deleteAccount(String authorization, UUID uuid, @Size(min=6) String password);

}
