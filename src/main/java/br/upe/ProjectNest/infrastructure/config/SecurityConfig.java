package br.upe.ProjectNest.infrastructure.config;

import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(handler -> handler
                .anyRequest().permitAll())
            .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
            .csrf(CsrfConfigurer::disable);

        return http.build();
    }

    @Bean
    public Algorithm algorithm(@Value("${projectnest.auth.jwt.secret}") String secret) {
        return Algorithm.HMAC256(secret);
    }


}
