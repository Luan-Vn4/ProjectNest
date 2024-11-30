package br.upe.ProjectNest.domain.common.exceptions.handlers;

import br.upe.ProjectNest.domain.common.exceptions.ExceptionBody;
import br.upe.ProjectNest.domain.common.utils.RequestUtils;
import jakarta.annotation.Priority;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@ControllerAdvice
@Order(0)
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionBody> handleValidationExceptions(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest req) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .error("Dados inválidos")
            .message(errors.toString())
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ExceptionBody> handleNoResourceFoundException(NoResourceFoundException ex,
                                                                        HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .error("Não foi possível achar o recurso")
            .message("Não há nenhum recurso disponibilizado nessa url")
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionBody> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                               HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.BAD_REQUEST.value())
            .error("O registro fornecido fere algumas restrições")
            .message(ex.getMessage())
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionBody> handleEntityNotFoundException(EntityNotFoundException ex,
                                                                       HttpServletRequest req) {
        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.NOT_FOUND.value())
            .error("Entidade não encontrada")
            .message(ex.getMessage())
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}
