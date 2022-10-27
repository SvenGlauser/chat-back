package ch.sven.chat.domain.validation;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

        ExceptionTestUtils.assertCoherenceThrownErrorList(validation::validate, "test");
    }

    @Test
    void notNull() {
        Validation.of(this.getClass())
                .notNull("notNull", "test", "test")
                .validate();

        Validation validation = Validation.of(this.getClass()).notNull(null, "test", "test");

        ExceptionTestUtils.assertCoherenceThrownErrorList(validation::validate, "test");
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