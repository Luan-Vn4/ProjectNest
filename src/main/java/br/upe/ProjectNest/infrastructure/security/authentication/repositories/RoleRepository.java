package br.upe.ProjectNest.infrastructure.security.authentication.repositories;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Short> {

    Optional<Role> findByName(String name);

    Set<Role> findByNameIn(Set<String> names);

    @Query("SELECT u.roles FROM Usuario u WHERE u.uuid = :usuarioUuid")
    Set<Role> findByUsuarioUuid(@Param("usuarioUuid") UUID usuarioUuid);

    @Query("SELECT u.roles FROM Usuario u WHERE u.email = :usuarioEmail")
    Set<Role> findByUsuarioEmail(@Param("usuarioEmail") String usuarioEmail);

}
