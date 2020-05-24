package lotto.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("돈 클래스 테스트")
class MoneyTest {
    private static final Money MONEY_1000 = Money.of(1000);
    private static final Money MONEY_500 = Money.of(500);
    private static final Money MONEY_100 = Money.of(100);

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 100, 1000, 10000})
    @DisplayName("초기화 테스트")
    void init(final int money) {
        assertThatCode(() -> Money.of(money)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("초기화 실패 : 음수값이 들어오면 예외 발생")
    void initFail() {
        assertThatIllegalArgumentException().isThrownBy(() -> Money.of(-1));
    }

    @Test
    @DisplayName("Equals 테스트")
    void equals() {
        assertThat(MONEY_1000).isEqualTo(Money.of(1000));
    }

    @Test
    @DisplayName("덧셈 테스트")
    void add() {
        assertThat(MONEY_1000).isEqualTo(MONEY_500.add(MONEY_500));
    }

    @Test
    @DisplayName("구입 가능 개수 테스트")
    void affordable() {
        assertThat(MONEY_1000.getAffordableCount(MONEY_100)).isEqualTo(10);
    }

    @Test
    @DisplayName("수익률 테스트")
    void profit() {
        assertThat(MONEY_1000.getProfitRate(MONEY_1000)).isOne();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Reduce 테스트")
    void reduce(final List<Money> moneys, Money expected) {
        Money sumOfMoney = moneys.stream()
                .reduce(Money.ZERO, Money::add);

        assertThat(sumOfMoney).isEqualTo(expected);
    }

    private static Stream<Arguments> reduce() {
        return Stream.of(
                Arguments.of(Arrays.asList(MONEY_100, MONEY_100, MONEY_100, MONEY_100, MONEY_100), MONEY_500),
                Arguments.of(Arrays.asList(MONEY_500, MONEY_500), MONEY_1000),
                Arguments.of(Collections.emptyList(), Money.ZERO)
        );
    }
}
