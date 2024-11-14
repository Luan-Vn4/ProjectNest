package br.upe.ProjectNest.domain.usuarios.models;

import br.upe.ProjectNest.infrastructure.security.authentication.authorities.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="empresas")
@Getter @Setter @NoArgsConstructor
public class Empresa extends Usuario {

    @Size(max=14) @CNPJ
    @Column(name="cnpj", length=14, updatable=false, unique=true)
    private @NotNull String cnpj;

    @Builder
    public Empresa(UUID uuid, String apelido, String email, String senha, Set<Role> roles, String cnpj) {
        super(uuid, apelido, email, senha, roles);
        this.cnpj = cnpj;
    }

    @Override
    public final boolean equals(Object object) {
        return super.equals(object) && ((Empresa) object).getCnpj().equals(this.getCnpj());
    }

    @Override
    public final int hashCode() {
        return super.hashCode() + cnpj.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + getUuid() + ", " +
                "cnpj = " + getCnpj() + ", " +
                "apelido = " + getApelido() + ", " +
                "email = " + getEmail() + ", " +
                "senha = " + getSenha() + ")";
    }

}
