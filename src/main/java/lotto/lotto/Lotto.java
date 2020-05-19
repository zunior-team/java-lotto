package lotto.lotto;

import lotto.dto.LottoTicketDto;
import lotto.dto.MatchResult;
import lotto.prize.LottoPrize;

import java.util.List;
import java.util.Map;

public class Lotto {
    private final int payment;
    private final LottoTickets lottoTickets;

    private Lotto(final int payment, final LottoTickets lottoTickets) {
        validate(lottoTickets);

        this.payment = payment;
        this.lottoTickets = lottoTickets;
    }

    private void validate(final LottoTickets lottoTickets) {
        if (lottoTickets == null) {
            throw new IllegalArgumentException("LottoTickets can't be a null");
        }
    }

    public static Lotto init(final int payment, final LottoTickets lottoTickets) {
        return new Lotto(payment, lottoTickets);
    }

    public List<LottoTicketDto> getLottoTickets() {
        return lottoTickets.getLottoTickets();
    }

    public MatchResult match(final WinningLotto winningLotto) {
        Map<LottoPrize, Long> lottoPrizes = lottoTickets.matchLottoNumbers(winningLotto);

        return MatchResult.init(payment, lottoPrizes);
    }
}
