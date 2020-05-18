package lotto.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.number.LottoNumber.MAX_NUM_OF_LOTTO_NUM;
import static lotto.number.LottoNumber.MIN_NUM_OF_LOTTO_NUM;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또에 사용하는 숫자에 대한 테스트")
public class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUM_OF_LOTTO_NUM, (MIN_NUM_OF_LOTTO_NUM + MAX_NUM_OF_LOTTO_NUM) / 2, MAX_NUM_OF_LOTTO_NUM})
    @DisplayName("초기화 테스트")
    void init(final int number) {
        assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {MIN_NUM_OF_LOTTO_NUM - 1, MAX_NUM_OF_LOTTO_NUM + 1})
    @DisplayName("초기화 실패 테스트")
    void initFail(final int number) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoNumber(number));
    }
}
