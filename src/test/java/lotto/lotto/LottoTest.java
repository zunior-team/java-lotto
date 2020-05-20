package lotto.lotto;

import lotto.dto.MatchResult;
import lotto.prize.LottoPrize;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

@DisplayName("구입한 로또에 대한 테스트")
class LottoTest {

    @Test
    @DisplayName("초기화 테스트")
    void init() {
        LottoTickets lottoTickets = Generator.lottoTickets(
                Generator.lottoTicket(1, 2, 3, 4, 5, 6),
                Generator.lottoTicket(1, 2, 3, 4, 5, 6)
        );

        assertThatCode(() -> Lotto.init(1000, lottoTickets))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("초기화 실패 테스트")
    void initFail(final LottoTickets lottoTickets) {
        assertThatIllegalArgumentException().isThrownBy(() -> Lotto.init(1000, lottoTickets));

    }

    @Test
    @DisplayName("당첨번호와 매치")
    void match() {
        final int payment = 10000;
        final WinningLotto winningLotto = Generator.winningLotto(1, 2, 3, 4, 5, 6);

        Lotto lotto = LottoSeller.buy(payment);
        MatchResult matchResult = lotto.match(winningLotto);

        assertThat(matchResult.getPayment()).isEqualTo(payment);

        long sum = Arrays.stream(LottoPrize.values())
                .mapToLong(matchResult::count)
                .sum();

        assertThat(sum).isEqualTo(payment / LottoSeller.PRICE_OF_A_TICKET);
    }

    @Test
    @DisplayName("구매한 로또 가져오기 가져오기")
    void getLottoTickets() {
        final int payment = 10000;
        Lotto lotto = LottoSeller.buy(payment);

        assertThat(lotto.getLottoTickets()).hasSize(payment / LottoSeller.PRICE_OF_A_TICKET);
    }
}