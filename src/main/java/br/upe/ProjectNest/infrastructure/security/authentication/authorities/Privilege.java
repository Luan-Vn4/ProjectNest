package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;

@Immutable @Entity
@Table(name="privileges")
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access=AccessLevel.PROTECTED)
public class Privilege implements GrantedAuthority {

    @Id
    @Column(name="id")
    private Short id;

    @Column(name="name", length=50, unique=true)
    private String name;

    @ManyToMany(mappedBy="privileges")
    private Set<Role> roles;

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return getName();
    }

}
