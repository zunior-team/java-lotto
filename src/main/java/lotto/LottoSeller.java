package lotto;

import lotto.lotto.LottoTicket;
import lotto.lotto.LottoTickets;
import lotto.number.LottoNumber;
import lotto.number.LottoNumberGenerator;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoSeller {
    private static final int PRICE_OF_A_TICKET = 1000;

    private LottoSeller() {}

    public static LottoTickets buy(final int payment) {
        validate(payment);

        return new LottoTickets(buyAutoTickets(payment));
    }

    private static List<LottoTicket> buyAutoTickets(int payment) {
        return Stream.generate(LottoSeller::generateAuto)
                    .limit(payment / PRICE_OF_A_TICKET)
                    .collect(Collectors.toList());
    }

    private static void validate(final int payment) {
        if (payment <= 0) {
            throw new IllegalArgumentException("Payment must be a positive number");
        }
    }

    private static LottoTicket generateAuto() {
        List<LottoNumber> lottoNumbers = LottoNumberGenerator.generate();

        return new LottoTicket(new LottoNumbers(lottoNumbers));
    }
}
