package lotto.lotto;

import lotto.dto.LottoTicketDto;
import lotto.prize.LottoPrize;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    public Map<LottoPrize, Long> matchLottoNumbers(final WinningNumbers winningNumbers) {
        return lottoTickets.stream()
                .map(lottoTicket -> lottoTicket.matchPrize(winningNumbers))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public LottoTickets addTickets(final LottoTickets autoTickets) {
        this.lottoTickets.addAll(autoTickets.lottoTickets);

        return this;
    }
}
