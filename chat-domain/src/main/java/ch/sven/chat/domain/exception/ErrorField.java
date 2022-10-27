package ch.sven.chat.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Classe contenant les erreurs à renvoyer au front
 */
@Getter
@RequiredArgsConstructor
public class ErrorField {
    private final String clazz;
    private final String field;
    private final String message;
}
