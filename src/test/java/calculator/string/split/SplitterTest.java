package calculator.string.split;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("구분자 테스트")
class SplitterTest {
    private static final DefaultSplitter SPLITTER = new DefaultSplitter();

    @ParameterizedTest
    @MethodSource
    @DisplayName("기본 구분자 , or | 로 잘 나뉘어 지는지")
    void splitWithDefaultSplitter(final String str, final String[] expected) {
        assertThat(SPLITTER.split(str)).containsExactly(expected);
    }

    private static Stream<Arguments> splitWithDefaultSplitter() {
        return Stream.of(
                Arguments.of("1,2", new String[]{"1", "2"}),
                Arguments.of("1:2", new String[]{"1", "2"}),
                Arguments.of("1,2:3", new String[]{"1", "2", "3"})
        );
    }
}