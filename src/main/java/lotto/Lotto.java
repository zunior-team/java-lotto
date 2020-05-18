package lotto;

import lotto.dto.LottoTicketDto;
import lotto.lotto.LottoTickets;

import java.util.List;

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
}
