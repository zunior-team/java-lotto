package lotto.match;

import lotto.money.Money;
import lotto.prize.LottoPrize;

import java.util.Arrays;
import java.util.Map;

public class MatchResult {
    private static final long DEFAULT_COUNT = 0L;

    private final Money paymentMoney;
    private final Map<LottoPrize, Long> matchResult;

    private MatchResult(final Money payment, final Map<LottoPrize, Long> matchResult) {
        this.paymentMoney = payment;
        this.matchResult = matchResult;
    }

    public static MatchResult init(final Money payment, final Map<LottoPrize, Long> matchResult) {
        return new MatchResult(payment, matchResult);
    }

    public double calculateEarningRate() {
        Money totalEarning = Arrays.stream(LottoPrize.values())
                .mapToInt(lottoPrize -> Math.toIntExact(lottoPrize.getPrizeMoney() * matchCount(lottoPrize)))
                .mapToObj(Money::of)
                .reduce(Money.ZERO, Money::add);

        return totalEarning.getProfitRate(paymentMoney);
    }

    public Long matchCount(final LottoPrize lottoPrize) {
        return matchResult.getOrDefault(lottoPrize, DEFAULT_COUNT);
    }
}
