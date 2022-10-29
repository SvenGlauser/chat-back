package ch.sven.chat.application.exception;

import ch.sven.chat.domain.exception.CoherenceException;
import ch.sven.chat.domain.exception.ErrorField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.ThrowableAssert;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.InstanceOfAssertFactories.ITERABLE;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionTestUtils {
    public static void assertCoherenceThrownErrorList(ThrowableAssert.ThrowingCallable throwingCallable, String... errors) {
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CoherenceException.class)
                .extracting(exception ->
                        ((CoherenceException) exception)
                                .getErrors()
                                .stream()
                                .map(ErrorField::getMessage)
                                .collect(Collectors.toList()), ITERABLE
                ).containsOnly(errors);
    }

    public static void assertCoherenceThrownError(ThrowableAssert.ThrowingCallable throwingCallable, String error) {
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CoherenceException.class)
                .extracting(Throwable::getMessage).isEqualTo(error);
    }
}
