package lotto.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static lotto.number.LottoNumber.MAX_NUM_OF_LOTTO_NUM;
import static lotto.number.LottoNumber.MIN_NUM_OF_LOTTO_NUM;
import static lotto.number.LottoNumberGenerator.BASE_INDEX;
import static lotto.number.LottoNumbers.NUM_OF_LOTTO_NUM;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("로또 넘버를 발급해주는 발급기 테스트")
public class LottoNumberGeneratorTest {

    @Test
    @DisplayName("발급 테스트")
    void issue() {
        List<LottoNumber> lottoNumbers = LottoNumberGenerator.generate();

        assertThat(lottoNumbers).hasSize(NUM_OF_LOTTO_NUM);
        assertThat(lottoNumbers.get(BASE_INDEX)).isLessThan(lottoNumbers.get(NUM_OF_LOTTO_NUM - 1));
        assertThat(lottoNumbers.get(BASE_INDEX)).isGreaterThanOrEqualTo(new LottoNumber(MIN_NUM_OF_LOTTO_NUM));
        assertThat(lottoNumbers.get(NUM_OF_LOTTO_NUM - 1)).isLessThanOrEqualTo(new LottoNumber(MAX_NUM_OF_LOTTO_NUM));
    }
}
