package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또를 파는 객체에 대한 테스트")
public class LottoSellerTest {
    @ParameterizedTest
    @CsvSource(value = {"1,0", "100,0", "1000,1", "2000,2", "10000,10", "11111,11"})
    @DisplayName("돈을 주면 해당하는 갯수만큼 판다.")
    void buy(final int money, final int expected) {
        Lotto lotto = LottoSeller.buy(money);

        assertThat(lotto.getLottoTickets()).hasSize(expected);
    }
}
