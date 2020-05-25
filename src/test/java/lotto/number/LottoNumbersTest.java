package lotto.number;

import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("로또 숫자 리스트를 가지고 있는 로또 넘버즈 테스트")
public class LottoNumbersTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 숫자 초기화 테스트")
    void init(final List<LottoNumber> lottoNumbers) {
        assertThatCode(() -> LottoNumbers.init(lottoNumbers)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(Generator.lottoNumberList(1, 2, 3, 4, 5, 6)),
                Arguments.of(Generator.lottoNumberList(1, 2, 3, 4, 5, 7))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 숫자 초기화 실패 테스트")
    void initFail(final List<LottoNumber> lottoNumbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumbers.init(lottoNumbers));
    }

    private static Stream<Arguments> initFail() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(Collections.emptyList()),
                Arguments.of(Generator.lottoNumberList(1)),
                Arguments.of(Generator.lottoNumberList(1, 2, 3, 4, 5))
        );
    }

    @Test
    @DisplayName("중복된 숫자로 생성하려고 할때 에러")
    void duplicatedNumber() {
        List<LottoNumber> lottoNumbers = Generator.lottoNumberList(1, 2, 3, 4, 5, 1);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> LottoNumbers.init(lottoNumbers));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("당첨 번호와 매치 테스트")
    void match(final LottoNumbers lottoNumbers, final LottoNumbers winningLottoNumbers, final int expected) {
        assertThat(lottoNumbers.matchCount(winningLottoNumbers)).isEqualTo(expected);
    }

    private static Stream<Arguments> match() {
        LottoNumbers winningLottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);

        return Stream.of(
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 6), winningLottoNumbers, 6),
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 5, 7), winningLottoNumbers, 5),
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 4, 7, 8), winningLottoNumbers, 4),
                Arguments.of(Generator.lottoNumbers(1, 2, 3, 7, 8, 9), winningLottoNumbers, 3),
                Arguments.of(Generator.lottoNumbers(1, 2, 7, 8, 9, 10), winningLottoNumbers, 2),
                Arguments.of(Generator.lottoNumbers(1, 7, 8, 9, 10, 11), winningLottoNumbers, 1),
                Arguments.of(Generator.lottoNumbers(7, 8, 9, 10, 11, 12), winningLottoNumbers, 0)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("번호를 포함하고 있는지 테스트")
    void contains(final LottoNumber bonusNumber, final boolean expected) {
        LottoNumbers lottoNumbers = Generator.lottoNumbers(1, 2, 3, 4, 5, 6);

        assertThat(lottoNumbers.contains(bonusNumber)).isEqualTo(expected);
    }

    private static Stream<Arguments> contains() {
        return Stream.of(
                Arguments.of(LottoNumber.of(1), true),
                Arguments.of(LottoNumber.of(7), false)
        );
    }
}
