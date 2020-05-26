package lotto.money;

import java.util.Objects;

public class Money {
    private static final int ZERO_VALUE = 0;
    public static final Money ZERO = Money.of(ZERO_VALUE);

    private final int money;

    private Money(final int money) {
        validate(money);

        this.money = money;
    }

    private void validate(int money) {
        if (money < ZERO_VALUE) {
            throw new IllegalArgumentException("Money value can't be negative");
        }
    }

    public static Money of(final int money) {
        return new Money(money);
    }

    public Money add(final Money anotherMoney) {
        return new Money(this.money + anotherMoney.money);
    }

    public Money sub(final Money anotherMoney) {
        return new Money(this.money - anotherMoney.money);
    }

    public int getAffordableCount(final Money price) {
        return this.money / price.money;
    }

    public double getProfitRate(final Money principal) {
        return (double) this.money / principal.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money anotherMoney = (Money) o;
        return money == anotherMoney.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
