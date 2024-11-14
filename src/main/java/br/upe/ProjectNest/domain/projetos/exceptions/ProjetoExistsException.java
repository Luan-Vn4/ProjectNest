package br.upe.ProjectNest.domain.projetos.exceptions;

public class ProjetoExistsException extends RuntimeException {

    public ProjetoExistsException(String parametro, String valor) {
        super("Já existe um projeto com " + parametro + " igual a " + valor );
    }
}
