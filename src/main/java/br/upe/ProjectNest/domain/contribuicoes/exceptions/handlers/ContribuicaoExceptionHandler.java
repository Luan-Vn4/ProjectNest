package br.upe.ProjectNest.domain.contribuicoes.exceptions.handlers;

import br.upe.ProjectNest.domain.contribuicoes.exceptions.ContribuicaoExistsException;
import br.upe.ProjectNest.domain.contribuicoes.exceptions.ContribuicaoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ContribuicaoExceptionHandler {

    @ExceptionHandler(ContribuicaoNotFoundException.class)
    public ResponseEntity<String> contribuicaoNotFound(ContribuicaoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ContribuicaoExistsException.class)
    public ResponseEntity<String> contribuicaoExists(ContribuicaoExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

}
