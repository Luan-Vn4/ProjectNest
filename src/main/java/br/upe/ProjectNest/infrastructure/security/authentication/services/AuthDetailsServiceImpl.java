package br.upe.ProjectNest.infrastructure.security.authentication.services;

import br.upe.ProjectNest.domain.usuarios.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<? extends UserDetails> user = usuarioRepository.findByEmailWithAuthorities(email);

        if (user.isEmpty()) throw new UsernameNotFoundException("Não foi identificado um usuário " +
            "com o email: " + email);

        return user.get();
    }

    @Transactional
    public UserDetails loadUserByUuid(UUID uuid) throws UsernameNotFoundException {
        Optional<? extends UserDetails> user = usuarioRepository.findByIdWithAuthorities(uuid);

        if (user.isEmpty()) throw new UsernameNotFoundException("Não foi identificado um usuário " +
            "com o UUID: " + uuid);

        return user.get();
    }

}
