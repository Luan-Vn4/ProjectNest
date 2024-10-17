package br.upe.ProjectNest.domain.usuarios.models;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="pessoas")
@Getter @Setter @NoArgsConstructor
public class Pessoa extends Usuario {

    @Size(max=50)
    @Column(name="nome", length=50)
    private @NotNull String nome;

    @Size(max=50)
    @Column(name="sobrenome", length=50)
    private @NotNull String sobrenome;

    @Size(max=25)
    @Column(name="pronomes", length=25)
    private @Nullable String pronomes;

    @Override
    public void merge(Usuario usuario) {
        super.merge(usuario);

        if (!(usuario instanceof Pessoa pessoa)) return;

        if (pessoa.getNome() != null && !pessoa.getNome().equals(getNome())) {
            setNome(pessoa.getNome());
        }
        if (pessoa.getSobrenome() != null && !pessoa.getSobrenome().equals(getSobrenome())) {
            setSobrenome(pessoa.getSobrenome());
        }
        if (pessoa.getPronomes() != null && !pessoa.getPronomes().equals(getPronomes())) {
            setPronomes(pessoa.getPronomes());
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
            "uuid = " + getUuid() + ", " +
            "nome = " + getNome() + ", " +
            "sobrenome = " + getSobrenome() + ", " +
            "pronomes = " + getPronomes() + ", " +
            "apelido = " + getApelido() + ", " +
            "email = " + getEmail() + ", " +
            "senha = " + getSenha() + ")";
    }

}
