package br.upe.ProjectNest.infrastructure.security.authentication.services.roles;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface RoleService {

    Set<Role> getByUsuarioUuuid(UUID usuarioUuid);

    Set<Role> getByUsuarioEmail(String usuarioEmail);

    /**
     * Permite obter um role a partir de seu nome. Caso o nome passado não tenha o prefixo "ROLE_"
     * ele será automaticamente inserido antes de buscar. Além disso, a busca sempre será feita em
     * uppercase. Para obter as opções de Roles disponíveis, veja as constantes definidas em {@link Role}.
     * @param name nome da role que você deseja buscar
     */
    Optional<Role> getByName(String name);

    /**
     * Permite obter as roles a partir de seu nome. Caso algum nome passado não tenha o prefixo "ROLE_"
     * ele será automaticamente inserido antes de buscar. Além disso, a busca sempre será feita em
     * uppercase. Para obter as opções de Roles disponíveis, veja as constantes definidas em {@link Role}.
     * @param names {@link Set} com o nome dos roles que deseja buscar
     */
    Set<Role> getByNames(Set<String> names);

}
