package ch.sven.chat.domain.exception;

import lombok.Getter;

import java.util.List;

/**
 * Exception pour renvoyer les messages d'erreurs
 */
@Getter
public class CoherenceException extends RuntimeException {
    private final List<ErrorField> errors;
    private final String clazz;
    private final String message;

    public CoherenceException(String clazz, String message) {
        this.clazz = clazz;
        this.message = message;
        this.errors = null;
    }

    public CoherenceException(List<ErrorField> errors) {
        this.errors = errors;
        this.clazz = null;
        this.message = null;
    }
}
