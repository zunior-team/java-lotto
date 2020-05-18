package lotto.number;

import lotto.util.Generator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또 숫자 리스트를 가지고 있는 로또 넘버즈 테스트")
public class LottoNumbersTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 숫자 초기화 테스트")
    void init(final List<LottoNumber> lottoNumbers) {
        assertThatCode(() -> new LottoNumbers(lottoNumbers)).doesNotThrowAnyException();
    }

    private static Stream<Arguments> init() {
        return Stream.of(
                Arguments.of(Generator.lottoNumberList("1", "2", "3", "4", "5", "6")),
                Arguments.of(Generator.lottoNumberList("1", "2", "3", "4", "5", "7"))
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("로또 숫자 초기화 실패 테스트")
    void initFail(final List<LottoNumber> lottoNumbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new LottoNumbers(lottoNumbers));
    }

    private static Stream<Arguments> initFail() {
        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(Collections.emptyList()),
                Arguments.of(Generator.lottoNumberList("1")),
                Arguments.of(Generator.lottoNumberList("1", "2", "3", "4", "5"))
        );
    }
}
