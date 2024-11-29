package br.upe.ProjectNest.infrastructure.security.authentication.exceptions;

import br.upe.ProjectNest.domain.common.exceptions.ExceptionBody;
import br.upe.ProjectNest.domain.common.utils.RequestUtils;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.ExpiredTokenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@ControllerAdvice
@Order(0)
public class AuthExceptionsHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionBody> handleBadCredentialsException(BadCredentialsException e,
                                                                       HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .error("Falha de autenticação")
            .message("As credenciais fornecidas são inválidas")
            .timeStamp(Instant.now())
            .request(RequestUtils.getFullRequestURL(req))
            .httpStatus(HttpStatus.UNAUTHORIZED.value())
            .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }


    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ExceptionBody> handleAccessDeniedException(AuthorizationDeniedException exception,
                                                                     HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.UNAUTHORIZED.value())
            .error("Acesso não autorizado")
            .message(exception.getMessage())
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(ExpiredTokenException.class)
    public ResponseEntity<ExceptionBody> handleExpiredTokenException(ExpiredTokenException exception,
                                                                     HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.UNAUTHORIZED.value())
            .error("Tokén expirado")
            .message(exception.getMessage())
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
