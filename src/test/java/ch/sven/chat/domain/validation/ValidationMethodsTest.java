package ch.sven.chat.domain.validation;

import ch.sven.chat.domain.exception.ExceptionTestUtils;
import org.junit.jupiter.api.Test;

class ValidationMethodsTest {

    @Test
    void valider() {
        ExceptionTestUtils.assertCoherenceThrownErrorList(() -> ((ValidationMethods) validation -> validation.notNull(null, "test", "test")).valider(), "test");
    }
}