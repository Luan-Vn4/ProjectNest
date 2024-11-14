package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import br.upe.ProjectNest.domain.usuarios.models.Empresa;
import br.upe.ProjectNest.domain.usuarios.models.Pessoa;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;
import java.util.Map;
import java.util.Set;

@Immutable @Entity
@Table(name="roles")
@Getter @NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Role implements GrantedAuthority {

    // ATRIBUTOS
    @Id
    @Column(name="id")
    private Short id;

    @Column(name="name", unique=true, length=50)
    private String name;

    @ManyToMany(mappedBy="roles")
    private Set<Usuario> usuarios;

    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(
        name="roles_privileges",
        joinColumns=@JoinColumn(name="id_role"),
        inverseJoinColumns=@JoinColumn(name="id_privilege")
    )
    private Set<Privilege> privileges;


    // CONSTANTES
    @Transient
    public static final String PESSOA = "ROLE_PESSOA";

    @Transient
    public static final String EMPRESA = "ROLE_EMPRESA";

    @Transient
    public static final String ADMIN = "ROLE_ADMIN";

    /**
     * Roles padrões, segundo o tipo de usuário
     */
    public static final Map<Class<? extends Usuario>, Set<String>> defaultRoles = Map.of(
        Pessoa.class, Set.of(Role.PESSOA),
        Empresa.class, Set.of(Role.EMPRESA)
    );


    // MÉTODOS
    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return getName();
    }
}
