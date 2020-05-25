package lotto.prize;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum LottoPrize {
    FIRST(6, 2_000_000_000, (matchCount, bonusMatch) -> matchCount == 6),
    SECOND(5, 30_000_000, (matchCount, bonusMatch) -> matchCount == 5 && bonusMatch),
    THIRD(5, 1_500_000, (matchCount, bonusMatch) -> matchCount == 5 && !bonusMatch),
    FOURTH(4, 50_000, (matchCount, bonusMatch) -> matchCount == 4),
    FIFTH(3, 5_000, (matchCount, bonusMatch) -> matchCount == 3),
    NONE(0, 0, (matchCount, bonusMatch) -> matchCount < 3);

    private final int matchCount;
    private final int prizeMoney;
    private final BiFunction<Integer, Boolean, Boolean> matcher;

    LottoPrize(final int matchCount, final int prizeMoney, final BiFunction<Integer, Boolean, Boolean> matcher) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
        this.matcher = matcher;
    }

    public static LottoPrize of(final int matchCount, final boolean isBonusMatch) {
        return Stream.of(values())
                .filter(prize -> prize.matcher.apply(matchCount, isBonusMatch))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Match info is not illegal"));
    }

    public static List<LottoPrize> getMeaningfulPrize() {
        List<LottoPrize> lottoPrizes = Arrays.asList(LottoPrize.values());

        lottoPrizes.remove(NONE);

        return lottoPrizes;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
