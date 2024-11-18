package br.upe.ProjectNest.domain.usuarios.repositories;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByEmail(String email);

    Page<Usuario> searchByApelidoContains(String apelido, Pageable pageable);

    Set<Usuario> findByUuidIn(Set<UUID> uuids);

    @Query("SELECT u FROM Usuario u WHERE u.uuid = :uuid")
    @EntityGraph(attributePaths = {"roles", "roles.privileges"})
    Optional<Usuario> findByIdWithAuthorities(@Param("uuid") UUID uuid);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    @EntityGraph(attributePaths = {"roles", "roles.privileges"})
    Optional<Usuario> findByEmailWithAuthorities(@Param("email") String email);

}