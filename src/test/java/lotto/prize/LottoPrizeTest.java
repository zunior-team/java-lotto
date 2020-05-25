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
    void match(final int matchCount, final boolean isBonusMatch, final LottoPrize expected) {
        assertThat(LottoPrize.of(matchCount, isBonusMatch)).isEqualTo(expected);
    }

    private static Stream<Arguments> match() {
        return Stream.of(
                Arguments.of(6, false, LottoPrize.FIRST),
                Arguments.of(6, true, LottoPrize.FIRST),
                Arguments.of(5, true, LottoPrize.SECOND),
                Arguments.of(5, false, LottoPrize.THIRD),
                Arguments.of(4, false, LottoPrize.FOURTH),
                Arguments.of(4, true, LottoPrize.FOURTH),
                Arguments.of(3, false, LottoPrize.FIFTH),
                Arguments.of(3, true, LottoPrize.FIFTH),
                Arguments.of(2, false, LottoPrize.NONE),
                Arguments.of(2, true, LottoPrize.NONE),
                Arguments.of(1, false, LottoPrize.NONE),
                Arguments.of(1, true, LottoPrize.NONE),
                Arguments.of(0, false, LottoPrize.NONE),
                Arguments.of(0, true, LottoPrize.NONE)
        );
    }
}
