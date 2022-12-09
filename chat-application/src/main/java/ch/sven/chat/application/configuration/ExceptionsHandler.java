package ch.sven.chat.application.configuration;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(CoherenceException.class)
    public ResponseEntity<List<ErrorField>> coherenceException(CoherenceException coherenceException) {
        return new ResponseEntity<>(coherenceException.getErrors(), HttpStatus.NOT_ACCEPTABLE);
    }
}
