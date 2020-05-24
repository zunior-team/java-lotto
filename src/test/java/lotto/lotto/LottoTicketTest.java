package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;
import lotto.prize.LottoPrize;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

@DisplayName("로또 티켓 한장에 대한 테스트")
public class LottoTicketTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 티켓은 숫자를 받아서 초기화")
    void init(final LottoNumbers lottoNumbers) {
        assertThatCode(() -> new LottoTicket(lottoNumbers))
                .doesNotThrowAnyException();
    }

    private static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 6)),
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 7))
        );
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("로또 티켓을 초기화 하기 위한 값이 비정상적일 때")
    void initFail(final LottoNumbers lottoNumbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoTicket(lottoNumbers));
    }


    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 티켓과 당첨 번호간의 매치후 당첨 타입 찾기")
    void matchPrize(final LottoTicket lottoTicket, final WinningNumbers winningNumbers, final LottoPrize lottoPrize) {
        assertThat(lottoTicket.matchPrize(winningNumbers)).isEqualByComparingTo(lottoPrize);
    }

    private static Stream<Arguments> matchPrize() {
        final WinningNumbers winningNumbers = WinningNumbers.init(
                Generator.lottoNumbers(1, 2, 3, 4, 5, 6),
                LottoNumber.of(7)
        );

        return Stream.of(
                Arguments.of(Generator.lottoTicket(1, 2, 3, 4, 5, 6), winningNumbers, LottoPrize.FIRST),
                Arguments.of(Generator.lottoTicket(1, 2, 3, 4, 5, 7), winningNumbers, LottoPrize.SECOND),
                Arguments.of(Generator.lottoTicket(1, 2, 3, 4, 5, 6), winningNumbers, LottoPrize.THIRD),
                Arguments.of(Generator.lottoTicket(1, 2, 3, 4, 7, 8), winningNumbers, LottoPrize.FOURTH),
                Arguments.of(Generator.lottoTicket(1, 2, 3, 7, 8, 9), winningNumbers, LottoPrize.FIFTH),
                Arguments.of(Generator.lottoTicket(1, 2, 7, 8, 9, 10), winningNumbers, LottoPrize.NONE),
                Arguments.of(Generator.lottoTicket(1, 7, 8, 9, 10, 11), winningNumbers, LottoPrize.NONE),
                Arguments.of(Generator.lottoTicket(7, 8, 9, 10, 11, 12), winningNumbers, LottoPrize.NONE)
        );
    }
}
