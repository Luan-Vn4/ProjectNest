package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthDetailsServiceImpl implements AuthDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<? extends UserDetails> user = usuarioRepository.findByEmail(email);

        if (user.isEmpty()) throw new UsernameNotFoundException("Não foi identificado um usuário " +
            "com o email: " + email);

        return user.get();
    }

    public UserDetails loadUserByUuid(UUID uuid) throws UsernameNotFoundException {
        Optional<? extends UserDetails> user = usuarioRepository.findById(uuid);

        if (user.isEmpty()) throw new UsernameNotFoundException("Não foi identificado um usuário " +
            "com o UUID: " + uuid);

        return user.get();
    }

}
