package lotto.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.number.LottoNumber.MAX_NUM_OF_LOTTO;
import static lotto.number.LottoNumber.MIN_NUM_OF_LOTTO;
import static org.assertj.core.api.Assertions.*;

@DisplayName("로또에 사용하는 숫자에 대한 테스트")
public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUM_OF_LOTTO, (MIN_NUM_OF_LOTTO + MAX_NUM_OF_LOTTO) / 2, MAX_NUM_OF_LOTTO})
    @DisplayName("초기화 테스트")
    void init(final int number) {
        assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUM_OF_LOTTO - 1, MAX_NUM_OF_LOTTO + 1})
    @DisplayName("초기화 실패 테스트")
    void initFail(final int number) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoNumber(number));
    }

    @Test
    @DisplayName("compare to 테스트")
    void compareTo() {
        LottoNumber numFive = new LottoNumber(5);
        LottoNumber numTen = new LottoNumber(10);

        assertThat(numFive).isLessThan(numTen);
        assertThat(numTen).isGreaterThan(numFive);
    }
}
