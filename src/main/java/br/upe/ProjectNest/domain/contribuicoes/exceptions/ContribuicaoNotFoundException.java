package br.upe.ProjectNest.domain.contribuicoes.exceptions;

import java.util.UUID;

public class ContribuicaoNotFoundException extends RuntimeException {

    public ContribuicaoNotFoundException(UUID id) {
        super("Não foi possível encontrar a contribuicao de id: " + id);
    }
}
