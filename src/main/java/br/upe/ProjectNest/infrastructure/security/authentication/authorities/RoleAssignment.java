package br.upe.ProjectNest.infrastructure.security.authentication.authorities;

import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name="usuario_role",
   uniqueConstraints=@UniqueConstraint(
       name = "UNIQUE_USUARIO_ROLE",
       columnNames={"role", "usuario_id"}))
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class RoleAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private @NotNull Role role;

    @JoinColumn(name="usuario_id")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private @NotNull Usuario usuario;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        RoleAssignment that = (RoleAssignment) o;
        return id != null && Objects.equals(id, that.id) && Objects.equals(role, that.role);
    }

    @Override
    public final int hashCode() {
        int classHash = this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
            : getClass().hashCode();

        return Objects.hash(id, role, classHash);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RoleAssignment.class.getSimpleName() + "[", "]")
            .add("id=" + id)
            .add("role=" + role)
            .toString();
    }

}
