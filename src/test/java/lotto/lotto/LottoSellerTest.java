package lotto.lotto;

import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또를 파는 객체에 대한 테스트")
public class LottoSellerTest {
    @ParameterizedTest
    @CsvSource(value = {"1,0", "100,0", "1000,1", "2000,2", "10000,10", "11111,11"})
    @DisplayName("돈을 주면 해당하는 갯수만큼 판다.")
    void buy(final int money, final int expected) {
        Lotto lotto = LottoSeller.buy(Generator.paymentInfo(money));

        assertThat(lotto.getLottoTickets()).hasSize(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1})
    @DisplayName("잘못된 지불금액을 받았을때 예외 발생")
    void buyFail(final int payment) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoSeller.buy(Generator.paymentInfo(payment)));
    }
}
