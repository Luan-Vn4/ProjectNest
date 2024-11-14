package br.upe.ProjectNest.infrastructure.security.tokens.exceptions;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message, Throwable cause) {
        super(message);
    }

}
