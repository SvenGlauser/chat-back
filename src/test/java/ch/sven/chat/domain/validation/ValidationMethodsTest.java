package ch.sven.chat.domain.validation;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;
import static org.junit.jupiter.api.Assertions.*;

class ValidationMethodsTest {

    @Test
    void valider() {
        assertThatThrownBy(() -> ((ValidationMethods) validation -> validation.notNull(null, "test", "test")).valider())
                .isInstanceOf(CoherenceException.class)
                .extracting(exception -> ((CoherenceException) exception).getErrors().stream().map(ErrorField::getMessage).collect(Collectors.toList()), ITERABLE)
                .containsOnly("test");
        }
}