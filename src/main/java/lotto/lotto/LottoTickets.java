package lotto.lotto;

import lotto.dto.LottoTicketDto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {
    private final List<LottoTicket> lottoTickets;

    private LottoTickets(final List<LottoTicket> lottoTickets) {
        validate(lottoTickets);

        this.lottoTickets = lottoTickets;
    }

    private void validate(final List<LottoTicket> lottoTickets) {
        if (lottoTickets == null) {
            throw new IllegalArgumentException("LottoTickets is null");
        }
    }

    public static LottoTickets init(final List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets);
    }

    public List<LottoTicketDto> getLottoTickets() {
        return lottoTickets.stream()
                .map(LottoTicketDto::of)
                .collect(Collectors.toList());
    }
}
