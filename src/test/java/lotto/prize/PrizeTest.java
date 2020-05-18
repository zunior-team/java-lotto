package lotto.prize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("당첨 테스트")
public class PrizeTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("매치되는 개수별 등수 테스트")
    void match(final int matchCount, final Prize expected) {
        assertThat(Prize.match(matchCount)).isEqualTo(expected);
    }

    private static Stream<Arguments> match() {
        return Stream.of(
                Arguments.of(6, Prize.FIRST),
                Arguments.of(5, Prize.SECOND),
                Arguments.of(4, Prize.THIRD),
                Arguments.of(3, Prize.FOURTH),
                Arguments.of(2, Prize.NONE),
                Arguments.of(1, Prize.NONE),
                Arguments.of(0, Prize.NONE)
        );
    }
}
