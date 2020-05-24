package lotto.match;

import lotto.prize.LottoPrize;

import java.util.Arrays;
import java.util.Map;

public class MatchResult {
    private static final long DEFAULT_COUNT = 0L;

    private final int payment;
    private final Map<LottoPrize, Long> matchResult;

    private MatchResult(final int payment, final Map<LottoPrize, Long> matchResult) {
        this.payment = payment;
        this.matchResult = matchResult;
    }

    public static MatchResult init(final int payment, final Map<LottoPrize, Long> matchResult) {
        return new MatchResult(payment, matchResult);
    }

    public double calculateEarningRate() {
        double earningSum = Arrays.stream(LottoPrize.values())
                .mapToDouble(lottoPrize -> (double) lottoPrize.getPrizeMoney() * matchCount(lottoPrize))
                .sum();

        return earningSum / payment;
    }

    public Long matchCount(final LottoPrize lottoPrize) {
        return matchResult.getOrDefault(lottoPrize, DEFAULT_COUNT);
    }
}
