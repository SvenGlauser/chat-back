package ch.sven.chat.domain.exception;

import lombok.Getter;

import java.util.List;

/**
 * Exception pour renvoyer les messages d'erreurs
 */
@Getter
public class CoherenceException extends RuntimeException {
    private final List<ErrorField> errors;

    public CoherenceException(String clazz, String message) {
        this.errors = List.of(new ErrorField(clazz, null, message));
    }

    public CoherenceException(List<ErrorField> errors) {
        this.errors = errors;
    }
}
