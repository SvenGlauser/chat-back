package ch.sven.chat.domain.exception;

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
                                .map(ErrorField::message)
                                .collect(Collectors.toList()), ITERABLE
                ).containsOnly(errors);
    }
}
