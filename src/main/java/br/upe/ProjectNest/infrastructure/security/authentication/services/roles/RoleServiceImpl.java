package br.upe.ProjectNest.infrastructure.security.authentication.services.roles;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import br.upe.ProjectNest.infrastructure.security.authentication.repositories.RoleRepository;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Role> getByUsuarioUuuid(UUID usuarioUuid) {
        return roleRepository.findByUsuarioUuid(usuarioUuid);
    }

    @Override
    public Set<Role> getByUsuarioEmail(@Email String usuarioEmail) {
        return roleRepository.findByUsuarioEmail(usuarioEmail);
    }

    @Override
    public Optional<Role> getByName(String name) {
        String normalizedName = normalizeRoleName(name);
        return roleRepository.findByName(normalizedName);
    }

    private String normalizeRoleName(String name) {
        if (!name.startsWith("ROLE_")) {
            name = "ROLE_" + name;
        }

        return name.toUpperCase();
    }

    @Override
    public Set<Role> getByNames(Set<String> names) {
        Set<String> normalizedNames = names.stream()
            .map(this::normalizeRoleName)
            .collect(Collectors.toSet());

        return roleRepository.findByNameIn(normalizedNames);
    }

}
