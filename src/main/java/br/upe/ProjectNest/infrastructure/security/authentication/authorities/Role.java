package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;
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
    @Fetch(FetchMode.SUBSELECT)
    private Set<Privilege> privileges;


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
