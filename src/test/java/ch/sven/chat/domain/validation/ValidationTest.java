package ch.sven.chat.domain.validation;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;

class ValidationTest {

    @Test
    void of() {
        Validation validation = Validation.of(this.getClass());
        assertThat(validation).isNotNull();
    }

    @Test
    void not() {
        Validation.of(this.getClass())
                .not(false, "test", "test")
                .validate();

        Validation validation = Validation.of(this.getClass()).not(true, "test", "test");

        assertThatThrownBy(validation::validate)
                .isInstanceOf(CoherenceException.class)
                .extracting(exception -> ((CoherenceException) exception).getErrors().stream().map(ErrorField::getMessage).collect(Collectors.toList()), ITERABLE)
                .containsOnly("test");
    }

    @Test
    void notNull() {
        Validation.of(this.getClass())
                .notNull("notNull", "test", "test")
                .validate();

        Validation validation = Validation.of(this.getClass()).notNull(null, "test", "test");

        assertThatThrownBy(validation::validate)
                .isInstanceOf(CoherenceException.class)
                .extracting(exception -> ((CoherenceException) exception).getErrors().stream().map(ErrorField::getMessage).collect(Collectors.toList()), ITERABLE)
                .containsOnly("test");
    }

    @Test
    void validate() {
        Validation.of(this.getClass())
                .not(false, "test", "test")
                .validate();

        Validation.of(this.getClass())
                .validate();

        Validation validation = Validation.of(this.getClass()).not(true, "test", "test");

        assertThatThrownBy(validation::validate).isInstanceOf(CoherenceException.class);
    }
}