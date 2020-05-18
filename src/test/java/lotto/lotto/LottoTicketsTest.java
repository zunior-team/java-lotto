package lotto.lotto;

import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("lotto tickets 테스트")
class LottoTicketsTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("초기화 테스트")
    void init(final List<LottoTicket> lottoTickets) {
        assertThatCode(() -> new LottoTickets(lottoTickets)).doesNotThrowAnyException();
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
                .isThrownBy(() -> new LottoTickets(lottoTickets));
    }
}