package lotto.lotto;

import lotto.view.LottoTicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    public LottoTickets(final List<LottoTicket> lottoTickets) {
        validate(lottoTickets);

        this.lottoTickets = lottoTickets;
    }

    private void validate(List<LottoTicket> lottoTickets) {
        if (lottoTickets == null) {
            throw new IllegalArgumentException("LottoTickets is null");
        }
    }

    public List<LottoTicketDto> getLottoTickets() {
        return lottoTickets.stream()
                .map(LottoTicketDto::of)
                .collect(Collectors.toList());
    }
}
