package lotto.lotto;

import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoSeller {
    static final int PRICE_OF_A_TICKET = 1000;
    private static final int ZERO = 0;

    private LottoSeller() {}

    public static Lotto buy(final int payment) {
        validate(payment);

        LottoTickets lottoTickets = LottoTickets.init(buyAutoLotto(payment));

        return Lotto.init(payment, lottoTickets);
    }

    private static List<LottoTicket> buyAutoLotto(final int payment) {
        return Stream.generate(LottoSeller::generateAuto)
                    .limit(payment / PRICE_OF_A_TICKET)
                    .collect(Collectors.toList());
    }

    private static void validate(final int payment) {
        if (payment <= ZERO) {
            throw new IllegalArgumentException("Payment must be a positive number");
        }
    }

    private static LottoTicket generateAuto() {
        return LottoTicket.init(LottoNumbers.auto());
    }
}
