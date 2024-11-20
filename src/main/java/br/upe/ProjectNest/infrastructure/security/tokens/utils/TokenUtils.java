package br.upe.ProjectNest.infrastructure.security.tokens.utils;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Optional;

public class TokenUtils {

    public static Optional<String> extractTokenFromRequest(@Nonnull HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return Optional.empty();
        return Optional.of(extractToken(authorization));
    }

    public static String extractToken(String token) {
        return token.replace("Bearer ", "");
    }

}
