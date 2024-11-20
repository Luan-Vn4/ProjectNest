package br.upe.ProjectNest.domain.common.exceptions.handlers;

import br.upe.ProjectNest.domain.common.exceptions.ExceptionBody;
import br.upe.ProjectNest.domain.common.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@Log4j2
@ControllerAdvice
@Order
public class UnhandledExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionBody> handleUnresolvedException(Exception ex, HttpServletRequest req) {
        log.error(ex.getMessage(), ex);

        var response = ExceptionBody.builder()
            .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .error("Erro Interno do Servidor")
            .message("Ocorreu um erro inesperado no servidor, tente novamente mais tarde")
            .request(RequestUtils.getFullRequestURL(req))
            .timeStamp(Instant.now())
            .build();

        return ResponseEntity.internalServerError().body(response);
    }

}
