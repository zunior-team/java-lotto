package calculator.string.split;

import calculator.string.token.IntTokens;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("커스텀 구분자 테스트 ")
class CustomSplitterTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("기본 구분자 , 와 | 에 추가로 커스텀 구분자를 추가했을때 잘 나뉘어 지는지")
    void splitWithDefaultSplitter(final String customSplitter, final String str, final IntTokens expected) {
        CustomSplitter splitter = CustomSplitter.init(customSplitter);

        assertThat(splitter.split(str)).isEqualTo(expected);
    }

    private static Stream<Arguments> splitWithDefaultSplitter() {
        return Stream.of(
                Arguments.of(".", "1,2.3", IntTokens.init(new String[]{"1", "2", "3"})),
                Arguments.of(".", "1:2.3", IntTokens.init(new String[]{"1", "2", "3"})),
                Arguments.of("+", "1,2:3+1", IntTokens.init(new String[]{"1", "2", "3", "1"}))
        );
    }
}