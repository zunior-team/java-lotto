package lotto.number;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {
    static final int MIN_NUM_OF_LOTTO = 1;
    static final int MAX_NUM_OF_LOTTO = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_POOL =
            IntStream.rangeClosed(MIN_NUM_OF_LOTTO, MAX_NUM_OF_LOTTO)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toMap(LottoNumber::toInt, Function.identity()));

    private final int number;

    private LottoNumber(final int number) {
        validate(number);

        this.number = number;
    }

    private void validate(final int number) {
        if (number < MIN_NUM_OF_LOTTO || MAX_NUM_OF_LOTTO < number) {
            throw new IllegalArgumentException(
                    "Lotto number must in between " + MIN_NUM_OF_LOTTO + " and " + MAX_NUM_OF_LOTTO
            );
        }
    }

    public static LottoNumber of(final int number) {
        if (!LOTTO_NUMBER_POOL.containsKey(number)) {
            return new LottoNumber(number);
        }

        return LOTTO_NUMBER_POOL.get(number);
    }

    public int toInt() {
        return number;
    }

    @Override
    public int compareTo(final LottoNumber anotherLottoNumber) {
        return this.number - anotherLottoNumber.number;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
