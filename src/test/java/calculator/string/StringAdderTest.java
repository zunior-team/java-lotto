package calculator.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덧셈 계산기")
class StringAdderTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("쉼표 또는 콜론을 구분자로 하여 덧셈을 한다.")
    void splitAndAdd(final String str, final int expected) {
        assertThat(StringAdder.calculate(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> splitAndAdd() {
        return Stream.of(
                Arguments.of("1,2", 3),
                Arguments.of("1:2", 3),
                Arguments.of("1,2,3", 6),
                Arguments.of("1,2:3", 6),
                Arguments.of("1:2:3", 6)
        );
    }
}