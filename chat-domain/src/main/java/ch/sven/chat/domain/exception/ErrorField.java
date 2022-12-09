package ch.sven.chat.domain.exception;

/**
 * Classe contenant les erreurs à renvoyer au front
 */
public record ErrorField(String clazz, String field, String message) {}
