package calculator.string.interpret;

import calculator.string.token.IntTokens;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("문자열을 토큰으로 해석하는 인터프리터 테스트")
class InterpreterTest {
    private static final String NORMAL_EXPRESSION = "1,2:3";
    private static final String CUSTOM_EXPRESSION = "//.\n1,2:3.4";

    @ParameterizedTest
    @MethodSource
    @DisplayName("문자열을 토큰으로 잘 해석하는지")
    void interpret(final String exp, final IntTokens expected) {
        assertThat(Interpreter.interpret(exp)).isEqualTo(expected);
    }

    private static Stream<Arguments> interpret() {
        return Stream.of(
                Arguments.of(NORMAL_EXPRESSION, IntTokens.init(new String[] {"1", "2", "3"})),
                Arguments.of(CUSTOM_EXPRESSION, IntTokens.init(new String[] {"1", "2", "3", "4"}))
        );
    }
}