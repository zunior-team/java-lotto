package lotto.prize;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum LottoPrize {
    FIRST(6, 2_000_000_000),
    SECOND(5, 1_500_000),
    THIRD(4, 50_000),
    FOURTH(3, 5_000),
    NONE(0, 0);

    private final int matchCount;
    private final int prizeMoney;

    private static final Map<Integer, LottoPrize> PRIZES = Arrays.stream(LottoPrize.values())
            .collect(Collectors.toMap(LottoPrize::getMatchCount, Function.identity()));

    LottoPrize(final int matchCount, final int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static LottoPrize of(final int matchCount) {
        return PRIZES.getOrDefault(matchCount, NONE);
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
