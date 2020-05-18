package lotto.view;

import lotto.lotto.LottoTicket;

import java.util.List;

public class LottoTicketDto {
    private final List<Integer> numbers;

    public LottoTicketDto(final LottoTicket lottoTicket) {
        this.numbers = lottoTicket.getNumbers();
    }

    public static LottoTicketDto of(final LottoTicket lottoTicket) {
        return new LottoTicketDto(lottoTicket);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
