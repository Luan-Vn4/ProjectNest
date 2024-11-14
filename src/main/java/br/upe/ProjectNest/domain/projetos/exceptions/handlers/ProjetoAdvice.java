package br.upe.ProjectNest.domain.projetos.exceptions.handlers;

import br.upe.ProjectNest.domain.projetos.exceptions.ProjetoExistsException;
import br.upe.ProjectNest.domain.projetos.exceptions.ProjetoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjetoAdvice {

    @ExceptionHandler(ProjetoNotFoundException.class)
    public ResponseEntity<String> projetoNotFound(ProjetoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ProjetoExistsException.class)
    public ResponseEntity<String> projetoExists(ProjetoExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> unknownError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Algo inesperado aconteceu. Tente novamente mais tarde!");
    }
}
