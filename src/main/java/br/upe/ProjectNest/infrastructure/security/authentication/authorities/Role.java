package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    PESSOA("Pessoa", "ROLE_PESSOA"),
    ADMIN("Administrador", "ROLE_ADMIN"),
    EMPRESA("Empresa", "ROLE_EMPRESA"),;


    // ATRIBUTOS
    private final String label;

    private final String prefixedRole;

    // MÉTODOS
    @Override
    public String getAuthority() {
        return this.getPrefixedRole();
    }

}
