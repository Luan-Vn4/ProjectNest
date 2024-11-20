package br.upe.ProjectNest.domain.projetos.exceptions;

import java.util.UUID;

public class ProjetoNotFoundException extends RuntimeException {

    public ProjetoNotFoundException(UUID id) {
        super("Não foi possível encontrar o projeto de id: " + id);
    }
}
