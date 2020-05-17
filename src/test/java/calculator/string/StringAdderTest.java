package calculator.string;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("덧셈 계산기")
class StringAdderTest {
    @ParameterizedTest
    @MethodSource
    @DisplayName("쉼표 또는 콜론을 구분자로 하여 덧셈함")
    void splitAndAdd(final String expStr, final int expected) {
        assertThat(StringAdder.calculate(expStr)).isEqualTo(expected);
    }

    private static Stream<Arguments> splitAndAdd() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("1,2", 3),
                Arguments.of("1:2", 3),
                Arguments.of("1,2,3", 6),
                Arguments.of("1,2:3", 6),
                Arguments.of("1:2:3", 6)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("빈값이거나 null일경우 0을 리턴함")
    void nullAndEmpty(final String expStr) {
        assertThat(StringAdder.calculate(expStr)).isZero();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("커스텀 또는 쉼표 또는 콜론을 구분자로 하여 덧셈함")
    void customSplitter(final String expStr, final int expected) {
        assertThat(StringAdder.calculate(expStr)).isEqualTo(expected);
    }

    private static Stream<Arguments> customSplitter() {
        return Stream.of(
                Arguments.of("//;\n1;2;3", 6),
                Arguments.of("//;\n1,2;3", 6),
                Arguments.of("//;\n1:2;3", 6),
                Arguments.of("//;\n1,2:3;4", 10),
                Arguments.of("//-\n1,2:3-4", 10),
                Arguments.of("//=\n1,2:3=4", 10)
        );
    }
}