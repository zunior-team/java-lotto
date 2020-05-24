package lotto.lotto;

import lotto.number.LottoNumbers;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("당첨 번호에 대한 테스트")
class WinningNumbersTest {

    @Test
    @DisplayName("숫자 리스트로 초기화 테스트")
    void initWithList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> WinningNumbers.init(numbers)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 넘버 객체로 초기화 테스트")
    void initWithLottoNumbers() {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> WinningNumbers.init(lottoNumbers)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("숫자 리스트로 초기화 실패")
    void initFailWithLit(final List<Integer> numbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(numbers));
    }

    @ParameterizedTest
    @NullSource
    @DisplayName("로또 넘버 객체로 초기화 실패")
    void initFailWithLit(final LottoNumbers lottoNumbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(lottoNumbers));
    }
}