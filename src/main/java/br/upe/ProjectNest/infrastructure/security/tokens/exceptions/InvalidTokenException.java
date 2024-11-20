package br.upe.ProjectNest.infrastructure.security.tokens.exceptions;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

    private final String token;

    public InvalidTokenException(String token) {
        super("O token fornecido \"%s\" é inválido".formatted(token));
        this.token = token;
    }

    public InvalidTokenException(String token, String message) {
        super(message);
        this.token = token;
    }

    public InvalidTokenException(String token, Throwable cause) {
        super("O token fornecido \"%s\" é inválido".formatted(token), cause);
        this.token = token;
    }

}
