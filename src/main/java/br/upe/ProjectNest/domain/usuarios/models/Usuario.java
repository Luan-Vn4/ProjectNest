package br.upe.ProjectNest.domain.usuarios.models;

import br.upe.ProjectNest.domain.common.Mergeable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;
import java.util.UUID;

@Entity
@DynamicUpdate
@Table(name="usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor
public class Usuario implements Mergeable<Usuario> {

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
