package lotto.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("구입 정보에 대한 테스트")
class PaymentInfoTest {
    private static final Money MONEY_2000 = Money.of(2000);

    @Test
    @DisplayName("초기화 테스트")
    void init() {
        List<List<Integer>> lottoNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 7)
        );

        assertThatCode(() -> PaymentInfo.init(MONEY_2000, lottoNumbers));
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("null로 인한 초기화 실패 테스트")
    void initFailWithNull(final Money payment, final List<List<Integer>> lottoNumbers) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> PaymentInfo.init(payment, lottoNumbers));
    }

    private static Stream<Arguments> initFailWithNull() {
        return Stream.of(
                Arguments.of(MONEY_2000, null),
                Arguments.of(null, Collections.emptyList()),
                Arguments.of(null, null)
        );
    }

    @Test
    @DisplayName("구입 가능한 수동 로또 숫자 초과 인한 초기화 실패 테스트")
    void initFailWithOverInput() {
        List<List<Integer>> lottoNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3, 4, 5, 6),
                Arrays.asList(1, 2, 3, 4, 5, 7),
                Arrays.asList(1, 2, 3, 4, 5, 8)
        );

        assertThatIllegalArgumentException()
                .isThrownBy(() -> PaymentInfo.init(MONEY_2000, lottoNumbers));
    }
}
