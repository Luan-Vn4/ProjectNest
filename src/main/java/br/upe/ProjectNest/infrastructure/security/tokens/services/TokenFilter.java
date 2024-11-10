package br.upe.ProjectNest.infrastructure.security.tokens.services;

import br.upe.ProjectNest.infrastructure.security.authentication.services.AuthDetailsService;
import br.upe.ProjectNest.infrastructure.security.tokens.dtos.TokenDTO;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private AuthDetailsService authDetailsService;

    private TokenService tokenService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        Optional<String> token = extractToken(request);

        if (token.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        TokenDTO tokenDTO = tokenService.validateToken(token.get());
        UserDetails userDetails = authDetailsService.loadUserByUuid(UUID.fromString(tokenDTO.subject()));

        var authentication = new UsernamePasswordAuthenticationToken(
            userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Optional<String> extractToken(@Nonnull HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) return Optional.empty();
        return Optional.of(authorization.replace("Bearer ", ""));
    }

}
