package br.upe.ProjectNest.domain.usuarios.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="empresas")
@Getter @Setter @NoArgsConstructor
public class Empresa extends Usuario {

    @Column(name="cnpj", length=14, updatable=false, unique=true)
    private @NotNull String cnpj;

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
