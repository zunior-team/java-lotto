package lotto.lotto;

import lotto.dto.LottoTicketDto;
import lotto.match.MatchResult;
import lotto.money.PaymentInfo;
import lotto.prize.LottoPrize;

import java.util.List;
import java.util.Map;

public class Lotto {
    private final PaymentInfo paymentInfo;
    private final LottoTickets lottoTickets;

    private Lotto(final PaymentInfo paymentInfo, final LottoTickets lottoTickets) {
        validate(paymentInfo, lottoTickets);

        this.paymentInfo = paymentInfo;
        this.lottoTickets = lottoTickets;
    }

    private void validate(final PaymentInfo paymentInfo, final LottoTickets lottoTickets) {
        if (paymentInfo == null || lottoTickets == null) {
            throw new IllegalArgumentException("LottoTickets can't be a null");
        }
    }

    public static Lotto init(final PaymentInfo paymentInfo, final LottoTickets lottoTickets) {
        return new Lotto(paymentInfo, lottoTickets);
    }

    public int getNoOfManualLotto() {
        return paymentInfo.getNoOfManualLotto();
    }

    public List<LottoTicketDto> getLottoTickets() {
        return lottoTickets.getLottoTickets();
    }

    public MatchResult match(final WinningNumbers winningNumbers) {
        Map<LottoPrize, Long> lottoPrizes = lottoTickets.matchLottoNumbers(winningNumbers);

        return MatchResult.init(paymentInfo.getPayment(), lottoPrizes);
    }
}
