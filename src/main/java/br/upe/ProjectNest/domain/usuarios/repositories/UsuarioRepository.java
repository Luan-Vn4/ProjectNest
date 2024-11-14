package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> searchByApelidoContains(String apelido, Pageable pageable);

    Set<Usuario> findByUuidIn(Set<UUID> uuids);
}