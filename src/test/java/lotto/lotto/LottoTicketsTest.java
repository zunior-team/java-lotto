package lotto.lotto;

import lotto.prize.LottoPrize;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("lotto tickets 테스트")
class LottoTicketsTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("초기화 테스트")
    void init(final List<LottoTicket> lottoTickets) {
        assertThatCode(() -> LottoTickets.init(lottoTickets)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(Arrays.asList(
                        Generator.lottoTicket(1, 2, 3, 4, 5, 6),
                        Generator.lottoTicket(1, 2, 3, 4, 5, 7),
                        Generator.lottoTicket(1, 2, 3, 4, 5, 8)
                ))
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("초기화 실패")
    void initFail(final List<LottoTicket> lottoTickets) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoTickets.init(lottoTickets));
    }

    @Test
    @DisplayName("로또 티켓 여러장과 당첨 번호 매치 결과 테스트")
    void matchLottoNumbers() {
        WinningNumbers winningNumbers = Generator.winningLotto(1, 2, 3, 4, 5, 6);
        LottoTickets lottoTickets = Generator.lottoTickets(
                Generator.lottoTicket(1, 2, 3, 4, 5, 6),
                Generator.lottoTicket(1, 2, 3, 4, 5, 7),
                Generator.lottoTicket(1, 2, 3, 4, 7, 8),
                Generator.lottoTicket(1, 2, 3, 7, 8, 9),
                Generator.lottoTicket(1, 2, 7, 8, 9, 10),
                Generator.lottoTicket(1, 7, 8, 9, 10, 11),
                Generator.lottoTicket(7, 8, 9, 10, 11, 12)
        );

        Map<LottoPrize, Long> lottoPrizes = lottoTickets.matchLottoNumbers(winningNumbers);

        assertThat(lottoPrizes.get(LottoPrize.FIRST)).isEqualTo(1L);
        assertThat(lottoPrizes.get(LottoPrize.SECOND)).isEqualTo(1L);
        assertThat(lottoPrizes.get(LottoPrize.THIRD)).isEqualTo(1L);
        assertThat(lottoPrizes.get(LottoPrize.FOURTH)).isEqualTo(1L);
        assertThat(lottoPrizes.get(LottoPrize.NONE)).isEqualTo(3L);
    }
}