package lotto.view;

import lotto.dto.LottoTicketDto;
import lotto.match.MatchResult;
import lotto.prize.LottoPrize;

import java.util.Arrays;
import java.util.List;

public class ConsoleOutput {
    private static final String BUY_INFO_FORMAT = "%d개를 구매했습니다.";
    private static final String WIN_PRIZE_STATISTICS_STATEMENT = "당첨 통계\n----------";
    private static final String LOTTO_PRIZE_SUMMARY_FORMAT = "%d개 일치 (%d원) - %d개";
    private static final String LOTTO_SECOND_PRIZE_SUMMARY_FORMAT = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String EARNINGS_RATE_FORMAT = "총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private ConsoleOutput() {}

    public static void showLottoTickets(final List<LottoTicketDto> lottoTickets) {
        System.out.println(String.format(BUY_INFO_FORMAT, lottoTickets.size()));

        lottoTickets.stream()
                .map(LottoTicketDto::getNumbers)
                .map(List::toArray)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }

    public static void showMatchResult(final MatchResult matchResult) {
        System.out.println(WIN_PRIZE_STATISTICS_STATEMENT);

        LottoPrize.getMeaningfulPrize()
                .forEach(lottoPrize -> showPrizeResult(lottoPrize, matchResult));

        System.out.println(String.format(EARNINGS_RATE_FORMAT, matchResult.calculateEarningRate()));
    }

    private static void showPrizeResult(final LottoPrize lottoPrize, final MatchResult matchResult) {
        String summaryFormat = lottoPrize == LottoPrize.SECOND ?
                LOTTO_SECOND_PRIZE_SUMMARY_FORMAT :
                LOTTO_PRIZE_SUMMARY_FORMAT;


        String summary = String.format(
                summaryFormat,
                lottoPrize.getMatchCount(),
                lottoPrize.getPrizeMoney(),
                matchResult.matchCount(lottoPrize)
        );

        System.out.println(summary);
    }
}
