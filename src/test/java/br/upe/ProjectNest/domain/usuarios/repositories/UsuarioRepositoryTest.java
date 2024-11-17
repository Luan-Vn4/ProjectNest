package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import br.upe.ProjectNest.infrastructure.TestConfig;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.RoleNames;
import br.upe.ProjectNest.infrastructure.security.authentication.repositories.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest @Log4j2
@ContextConfiguration(classes=TestConfig.class)
@AutoConfigureTestDatabase(replace=NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void givenUsuario_WhenCreate_ThenReturnPessoa() {
        Usuario usuario = Pessoa.builder()
            .email("luan@gmail.com")
            .senha("$2a$12$PdcAjt7bzj2x2KxOPpkcT.H3jrqaWlcgrdUQQgLLgIV/9/I/tYE46")
            .apelido("Luan Gamer")
            .pronomes("Ele/Dele")
            .nome("Luan")
            .sobrenome("Vilaça")
            .roles(roleRepository.findByNameIn(RoleNames.defaultRoles.get(Pessoa.class)))
            .build();

        Usuario created = usuarioRepository.saveAndFlush(usuario);
        em.refresh(created);
        em.clear();

        Optional<Usuario> result = usuarioRepository.findById(created.getUuid());

        Assertions.assertFalse(result.isEmpty());
        log.info("Criado: {}", created);
        log.info("Roles do criado: {}", created.getAuthorities());
        log.info("Buscado: {}", result.get());
        log.info("Roles do buscado: {}", result.get().getAuthorities());
    }

}
