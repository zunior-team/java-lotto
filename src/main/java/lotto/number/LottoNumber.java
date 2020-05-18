package lotto.number;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {
    static final int MIN_NUM_OF_LOTTO_NUM = 1;
    static final int MAX_NUM_OF_LOTTO_NUM = 45;

    private final int number;

    public LottoNumber(int number) {
        validate(number);

        this.number = number;
    }

    private void validate(int number) {
        if (number < MIN_NUM_OF_LOTTO_NUM || MAX_NUM_OF_LOTTO_NUM < number) {
            throw new IllegalArgumentException(
                    "Lotto number must in between " + MIN_NUM_OF_LOTTO_NUM + " and " + MAX_NUM_OF_LOTTO_NUM
            );
        }
    }

    @Override
    public int compareTo(LottoNumber anotherLottoNumber) {
        return this.number - anotherLottoNumber.number;
    }

    @Override
    public boolean equals(Object o) {
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
