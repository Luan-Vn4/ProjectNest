package br.upe.ProjectNest.infrastructure.security.tokens.exceptions;

import lombok.Getter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
public class ExpiredTokenException extends InvalidTokenException {

    private final Instant expiration;

    public ExpiredTokenException(String token, Instant expiration) {
        super("O token \"%s\" expirou na data: %s".formatted(token, getParsedInstant(expiration)));
        this.expiration = expiration;
    }

    public ExpiredTokenException(String token, Instant expiration, String message) {
        super(token, message);
        this.expiration = expiration;
    }

    private static String getParsedInstant(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneOffset.UTC).toString();
    }

    public LocalDateTime getExpirationAsLocalDateTime() {
        return LocalDateTime.ofInstant(expiration, ZoneOffset.UTC);
    }

}
