package br.upe.ProjectNest.infrastructure.exceptionhandlers;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * Esse filtro é utilizado para capturar exceções que ocorram no securityFilterChain do Spring, pois
 * exceções lançadas pelos filtros não são resolvidas pelos ExceptionHandlers globais da aplicação.
 * Essa classe precisa ser adicionada antes de outros filtros do FilterChain que possam lançar exceções.
 * Assim, quando uma exceção for lançada, ela irá subir o stack de chamada dos filtros até voltar para
 * esse filtro que irá lidar com a exceção
 */
@Component
public class FilterExceptionHandler extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final HandlerExceptionResolver exceptionResolver;

    public FilterExceptionHandler(@Qualifier("handlerExceptionResolver")
                                  HandlerExceptionResolver exceptionResolver) {
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) {
        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.debug("Erro na FilterChain do Spring", e);
            exceptionResolver.resolveException(request, response, null, e);
        }
    }

}
