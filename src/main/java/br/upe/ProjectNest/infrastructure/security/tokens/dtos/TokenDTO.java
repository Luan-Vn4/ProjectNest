package br.upe.ProjectNest.infrastructure.security.tokens.dtos;

import java.time.Instant;

public record TokenDTO(boolean valid, String token, String tokenType,
                       String subject, Instant expiration) {

}
