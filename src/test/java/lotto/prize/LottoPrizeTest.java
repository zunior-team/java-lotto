package lotto.prize;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("당첨 테스트")
public class LottoPrizeTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("매치되는 개수별 등수 테스트")
    void match(final int matchCount, final LottoPrize expected) {
        assertThat(LottoPrize.of(matchCount)).isEqualTo(expected);
    }

    private static Stream<Arguments> match() {
        return Stream.of(
                Arguments.of(6, LottoPrize.FIRST),
                Arguments.of(5, LottoPrize.SECOND),
                Arguments.of(4, LottoPrize.THIRD),
                Arguments.of(3, LottoPrize.FOURTH),
                Arguments.of(2, LottoPrize.NONE),
                Arguments.of(1, LottoPrize.NONE),
                Arguments.of(0, LottoPrize.NONE)
        );
    }
}
