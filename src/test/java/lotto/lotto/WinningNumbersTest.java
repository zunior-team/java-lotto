package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;
import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("당첨 번호에 대한 테스트")
class WinningNumbersTest {

    @Test
    @DisplayName("숫자 리스트로 초기화 테스트")
    void initWithList() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatCode(() -> WinningNumbers.init(numbers, 7)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("로또 넘버 객체로 초기화 테스트")
    void initWithLottoNumbers() {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(7);

        assertThatCode(() -> WinningNumbers.init(lottoNumbers, bonusNumber)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("숫자 리스트로 초기화 실패")
    void initFailWithList(final List<Integer> numbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(numbers, 7));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 넘버 객체로 초기화 실패")
    void initFailWithLottoObj(final LottoNumbers lottoNumbers, final LottoNumber bonusNumber) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(lottoNumbers, bonusNumber));
    }

    private static Stream<Arguments> initFailWithLottoObj() {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(7);

        return Stream.of(
                Arguments.of(lottoNumbers, null),
                Arguments.of(null, bonusNumber),
                Arguments.of(null, null)
        );
    }

    @Test
    @DisplayName("숫자 리스트로 초기화 숫자 중복으로 실패")
    void initFailWithDuplication() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(numbers, 6));
    }

    @Test
    @DisplayName("로또 넘버 객체로 초기화 숫자 중복으로 실패")
    void numberObjInitFailWithDuplication() {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(6);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> WinningNumbers.init(lottoNumbers, bonusNumber));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("보너스 숫자 매치 테스트")
    void bonusMatch(final LottoNumbers target , final boolean expected) {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumber bonusNumber = LottoNumber.of(7);
        WinningNumbers winningNumbers = WinningNumbers.init(lottoNumbers, bonusNumber);

        assertThat(winningNumbers.isBonusMatch(target)).isEqualTo(expected);
    }

    private static Stream<Arguments> bonusMatch() {
        return Stream.of(
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 6), false),
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 7), true)
        );
    }
}
