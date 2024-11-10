package br.upe.ProjectNest.infrastructure.security.tokens.services;

import br.upe.ProjectNest.infrastructure.security.tokens.dtos.TokenDTO;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.ExpiredTokenException;
import br.upe.ProjectNest.infrastructure.security.tokens.exceptions.InvalidTokenException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${projectnest.auth.jwt.issuer}")
    private String issuer;

    @Value("${projectnest.auth.jwt.expiration.unit}")
    private ChronoUnit expirationUnit;

    @Value("${projectnest.auth.jwt.expiration.time}")
    private Long expirationTime;

    private final Algorithm algorithm;

    /**
     * Gera um token com o sujeito especificado
     * @param subject subject que será inserido no token
     * @return {@link TokenDTO} com as informações do token gerado
     * @throws JWTCreationException caso não seja possível criar o token
     */
    public TokenDTO generateToken(@NotNull String subject) {
        try {
            Instant now = Instant.now();
            Instant expiration = getExpiration(now);

            String token = JWT.create()
                .withIssuer(issuer)
                .withSubject(subject)
                .withIssuedAt(now)
                .withExpiresAt(expiration)
                .sign(algorithm);

            return new TokenDTO(true, token, "Bearer", subject, expiration);
        } catch (IllegalArgumentException | JWTCreationException e) {
            log.error("Não foi possível criar o token para o subject {}", subject, e);
            throw e;
        }
    }

    /**
     * Valida o token fornecido
     * @param token que deseja verificar
     * @return {@link TokenDTO} com as informações do token retornado
     * @throws TokenExpiredException caso o token esteja expirado
     * @throws JWTVerificationException caso o token seja inválido
     */
    public TokenDTO validateToken(@NotNull String token) {
        try {
            DecodedJWT jwt = JWT
                .require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);

            Instant expiration = jwt.getExpiresAtAsInstant();

            return new TokenDTO(true, token, "Bearer", jwt.getSubject(), expiration);
        } catch (TokenExpiredException exception) {
            throw new ExpiredTokenException(token, exception.getExpiredOn());
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException(token, exception);
        }
    }

    private Instant getExpiration(Instant now) {
        return now.plus(expirationTime, expirationUnit);
    }

}
