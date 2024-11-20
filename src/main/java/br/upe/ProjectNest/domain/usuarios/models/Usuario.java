package br.upe.ProjectNest.domain.usuarios.models;

import br.upe.ProjectNest.domain.common.Mergeable;
import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.Stream;

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

    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(
        name="usuarios_roles",
        joinColumns=@JoinColumn(name="uuid_usuario"),
        inverseJoinColumns=@JoinColumn(name="id_role")
    )
    @Fetch(FetchMode.SUBSELECT)
    private @NotNull Set<Role> roles = new HashSet<>();

    public Usuario(UUID uuid, @NotNull String apelido, @NotNull @Email String email,
                   @NotNull @Size(min=60, max=60) String senha, @Nullable Set<Role> roles) {
        this.uuid = uuid;
        this.apelido = apelido;
        this.email = email;
        this.senha = senha;
        this.roles = roles != null ? roles : new HashSet<>();
    }

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
        return roles.stream()
            .flatMap(x -> Stream.concat(Stream.of(x), x.getPrivileges().stream()))
            .toList();
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public void addRoles(Collection<Role> roles) {
        this.roles.addAll(roles);
    }

    public Set<Role> getRoles() {
        return Set.copyOf(roles);
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
