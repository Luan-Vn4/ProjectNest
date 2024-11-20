package br.upe.ProjectNest.domain.projetos.models;

import br.upe.ProjectNest.domain.projetos.models.enums.Escopo;
import br.upe.ProjectNest.domain.projetos.models.enums.Status;
import br.upe.ProjectNest.domain.usuarios.models.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "projetos")
@NoArgsConstructor @Getter @Setter
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID )
    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "id_dono", nullable = false)
    private Usuario dono;

    @Column(name = "titulo", nullable = false, length = 100)
    private @NotNull String titulo;

    @Column(name = "descricao", nullable = false)
    private @NotNull String descricao;

    @Column(name = "url_repositorio", unique = true)
    private String urlRepositorio;

    @Enumerated(EnumType.STRING)
    private Escopo escopo;

    @Enumerated(EnumType.STRING)
    private Status status;

}
