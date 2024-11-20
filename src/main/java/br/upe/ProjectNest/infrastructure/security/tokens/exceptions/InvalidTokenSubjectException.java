package br.upe.ProjectNest.infrastructure.security.tokens.exceptions;

import lombok.Getter;

/**
 * Utilizado quando o subject de algum token não representar nenhuma entidade no sistema.
 * Por exemplo, caso o usuário tenha apagado sua conta, o token em si continuará válido, em teoria,
 * mas ele não estará ligado a nenhuma entidade, não fazendo sentido mantê-lo válido.
 */
@Getter
public class InvalidTokenSubjectException extends InvalidTokenException {

    private final String subject;

    public InvalidTokenSubjectException(String token, String subject) {
        super(token, "O subject \"%s\" do token \"%s\" é inválido".formatted(token, subject));
        this.subject = subject;
    }

    public InvalidTokenSubjectException(String token, String subject, String message) {
        super(token, message);
        this.subject = subject;
    }

}
