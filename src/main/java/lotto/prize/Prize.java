package lotto.prize;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Prize {
    FIRST(6, 2000000000),
    SECOND(5, 1500000),
    THIRD(4, 50000),
    FOURTH(3, 5000),
    NONE(0, 0);

    private final int matchCount;
    private final int prizeMoney;

    private static final Map<Integer, Prize> PRIZES = Arrays.stream(Prize.values())
            .collect(Collectors.toMap(Prize::getMatchCount, Function.identity()));

    Prize(final int matchCount, final  int prizeMoney) {
        this.matchCount = matchCount;
        this.prizeMoney = prizeMoney;
    }

    public static Prize match(final int matchCount) {
        if (!PRIZES.containsKey(matchCount)) {
            return NONE;
        }

        return PRIZES.get(matchCount);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }
}
