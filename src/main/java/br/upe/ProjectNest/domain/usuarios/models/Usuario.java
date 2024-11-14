package br.upe.ProjectNest.domain.usuarios.models;

import br.upe.ProjectNest.domain.common.Mergeable;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.RoleAssignment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public abstract class Usuario implements Mergeable<Usuario>, UserDetails {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID uuid;

    @Size(max=50)
    @Column(name="apelido", unique=true, length=50)
    private @NotNull String apelido;

    @Email @Size(max=255)
    @Column(name="email", unique=true)
    private @NotNull String email;

    @Size(min=60, max=60)
    @Column(name="senha", length=60, columnDefinition="bpchar(60)")
    private @NotNull String senha;

    @OneToMany(mappedBy="usuario", orphanRemoval=true, cascade=CascadeType.ALL)
    private @NotNull List<RoleAssignment> roleAssignments;


    // MÉTODOS
    public void merge(Usuario usuario) {
        if (usuario.getApelido() != null && !usuario.getApelido().equals(getApelido())) {
            setApelido(usuario.getApelido());
        }
        if (usuario.getEmail() != null && !usuario.getEmail().equals(getEmail())) {
            setEmail(usuario.getEmail());
        }
        if (usuario.getSenha() != null && !usuario.getSenha().equals(getSenha())) {
            setSenha(usuario.getSenha());
        }
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public String getPassword() {
        return getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roleAssignments.stream().map(RoleAssignment::getRole).toList();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        Class<?> oEffectiveClass = object instanceof HibernateProxy
            ? ((HibernateProxy) object).getHibernateLazyInitializer().getImplementationClass()
            : object.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getImplementationClass()
            : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Usuario usuario = (Usuario) object;
        return getUuid() != null && Objects.equals(getUuid(), usuario.getUuid());
    }

    @Override
    public int hashCode() {
        int hashcode = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();

        return hashcode + Objects.hash(getUuid());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "uuid = " + getUuid() + ", " +
            "apelido = " + getApelido() + ", " +
            "email = " + getEmail() + ", " +
            "senha = " + getSenha() + ")";
    }

}
