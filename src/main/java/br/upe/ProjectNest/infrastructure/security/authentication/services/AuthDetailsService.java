package br.upe.ProjectNest.infrastructure.security.authentication.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface AuthDetailsService extends UserDetailsService {

    UserDetails loadUserByUuid(UUID uuid);

}
