package lotto.dto;

import lotto.prize.Prize;

import java.util.Map;

public class MatchResult {
    private final int payment;
    private final Map<Prize, Integer> matchResult;

    private MatchResult(final int payment, final Map<Prize, Integer> matchResult) {
        this.payment = payment;
        this.matchResult = matchResult;
    }

    public static MatchResult init(final int payment, final Map<Prize, Integer> matchResult) {
        return new MatchResult(payment, matchResult);
    }

}
