package br.upe.ProjectNest.domain.contribuicoes.models;

import br.upe.ProjectNest.domain.projetos.models.Projeto;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "contribuicoes")
@NoArgsConstructor
@Getter
@Setter
public class Contribuicao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToMany()
    @JoinTable(name = "contribuicoes_usuarios", joinColumns = @JoinColumn(name = "id_contribuicao"), inverseJoinColumns = @JoinColumn(name = "id_usuario"))
    private Set<Usuario> usuarios = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "id_projeto", nullable = false)
    private Projeto projeto;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "url_repositorio", unique = true)
    private String urlRepositorio;

    public void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void addUsuarios(Set<Usuario> novosUsuarios) {
        this.usuarios.addAll(novosUsuarios);
    }
}
