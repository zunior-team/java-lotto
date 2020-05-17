package calculator.string.interpret;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("계산식 테스트")
class ExpressionTest {
    private static final String NULL = null;
    private static final String EMPTY = "";
    private static final String ZERO = "0";
    private static final String NORMAL_EXPRESSION = "1,2:3";
    private static final String CUSTOM_SPLITTER_FORM = "//%s\n" + NORMAL_EXPRESSION;
    private static final String DOT_ATTACHED_EXP = String.format(CUSTOM_SPLITTER_FORM, ".");
    private static final String EQUAL_ATTACHED_EXP = String.format(CUSTOM_SPLITTER_FORM, "=");

    @ParameterizedTest
    @MethodSource
    @DisplayName("커스텀 계산식인지 확인")
    void isCustom(final String expression, final boolean expected) {
        assertThat(Expression.match(expression).isCustom()).isEqualTo(expected);
    }

    private static Stream<Arguments> isCustom() {
        return Stream.of(
                Arguments.of(NORMAL_EXPRESSION, false),
                Arguments.of(DOT_ATTACHED_EXP, true),
                Arguments.of(EQUAL_ATTACHED_EXP, true)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("식으로 부터 구분자와 식 추출 테스트")
    void extract(final String originExp, final String expectedSplitter, final String expectedExp) {
        Expression exp = Expression.match(originExp);

        assertThat(exp.extractCustomSplitter()).isEqualTo(expectedSplitter);
        assertThat(exp.extractExpression()).isEqualTo(expectedExp);
    }

    private static Stream<Arguments> extract() {
        return Stream.of(
                Arguments.of(DOT_ATTACHED_EXP, ".", NORMAL_EXPRESSION),
                Arguments.of(EQUAL_ATTACHED_EXP, "=", NORMAL_EXPRESSION)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("원 계산식을 잘 리턴하는지")
    void origin(final String expression, final String expected) {
        assertThat(Expression.match(expression).origin()).isEqualTo(expected);
    }

    private static Stream<Arguments> origin() {
        return Stream.of(
                Arguments.of(NULL, ZERO),
                Arguments.of(EMPTY, ZERO),
                Arguments.of(ZERO, ZERO),
                Arguments.of(NORMAL_EXPRESSION, NORMAL_EXPRESSION),
                Arguments.of(DOT_ATTACHED_EXP, DOT_ATTACHED_EXP),
                Arguments.of(EQUAL_ATTACHED_EXP, EQUAL_ATTACHED_EXP)
        );
    }
}