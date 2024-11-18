package br.upe.ProjectNest.infrastructure.security.authentication.repositories;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Set;

public interface PrivilegeRepository extends JpaRepository<Privilege, Short> {

    Privilege findByName(String name);

    @Query("SELECT r.privileges FROM Role r WHERE r.id = :roleId")
    Set<Privilege> findByRoleId(@Param("roleId") Short roleId);

    @Query("SELECT r.privileges FROM Role r WHERE r.name = :roleName")
    Set<Privilege> findByRoleId(@Param("roleName") String roleName);

}
