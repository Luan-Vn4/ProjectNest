package br.upe.ProjectNest.domain.contribuicoes.exceptions;

public class ContribuicaoExistsException extends RuntimeException {

    public ContribuicaoExistsException(String parametro, String valor) {
        super("Já existe uma contribuição com " + parametro + " igual a " + valor );
    }
}
