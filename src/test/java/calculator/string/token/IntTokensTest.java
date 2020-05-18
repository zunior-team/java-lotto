package calculator.string.token;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("숫자 토큰 테스트")
class IntTokensTest {
    private static final String[] ZERO = new String[] {"0"};
    private static final String[] ONE_TOKEN = new String[] {"1"};
    private static final String[] TWO_TOKENS = new String[] {"1", "2"};
    private static final String[] THREE_TOKENS = new String[] {"1", "2", "3"};
    private static final String[] NEGATIVE_CONTAINED_TOKENS = new String[] {"1", "-2", "3"};
    private static final String[] CHAR_CONTAINED_TOKENS = new String[] {"1", "-2", "hah"};

    @ParameterizedTest
    @MethodSource
    @DisplayName("초기화 테스트")
    void init(final String[] tokens) {
        assertThatCode(() -> IntTokens.init(tokens)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of((Object) ZERO),
                Arguments.of((Object) ONE_TOKEN),
                Arguments.of((Object) TWO_TOKENS),
                Arguments.of((Object) THREE_TOKENS)
        );
    }


    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("null 또는 빈 배열일 때 실패")
    void initFail(final String[] tokens) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> IntTokens.init(tokens));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("음수 혹은 문자가 있을때 초기화 실패")
    void initFailInputInvalid(final String[] tokens, final Class<?> exceptionClass) {
        assertThatThrownBy(() -> IntTokens.init(tokens))
                .isInstanceOf(exceptionClass);
    }

    public static Stream<Arguments> initFailInputInvalid() {
        return Stream.of(
                Arguments.of(NEGATIVE_CONTAINED_TOKENS, IllegalArgumentException.class),
                Arguments.of(CHAR_CONTAINED_TOKENS, NumberFormatException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("음수 혹은 숫자가 아닌 문자열이 있을때 초기화 실패")
    void calculate(final String[] tokens, final int expected) {
        IntTokens token = IntTokens.init(tokens);

        assertThat(token.calculate()).isEqualTo(expected);
    }

    public static Stream<Arguments> calculate() {
        return Stream.of(
                Arguments.of(ZERO, 0),
                Arguments.of(ONE_TOKEN, 1),
                Arguments.of(TWO_TOKENS, 3),
                Arguments.of(THREE_TOKENS, 6)
        );
    }
}