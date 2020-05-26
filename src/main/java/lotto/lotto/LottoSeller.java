package lotto.lotto;

import lotto.money.Money;
import lotto.money.PaymentInfo;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoSeller {
    public static final int PRICE_OF_A_TICKET_VALUE = 1000;
    public static final Money PRICE_OF_A_TICKET = Money.of(PRICE_OF_A_TICKET_VALUE);

    private LottoSeller() {}

    public static Lotto buy(final PaymentInfo paymentInfo) {
        LottoTickets manualTickets = LottoTickets.init(buyManualLottoTickets(paymentInfo.getLottoNumbers()));
        LottoTickets autoTickets = LottoTickets.init(buyAutoLottoTickets(paymentInfo.getChange()));

        return Lotto.init(paymentInfo, manualTickets.addTickets(autoTickets));
    }

    private static List<LottoTicket> buyAutoLottoTickets(final Money payment) {
        return Stream.generate(LottoSeller::generateAuto)
                .limit(payment.getAffordableCount(PRICE_OF_A_TICKET))
                .collect(Collectors.toList());
    }

    private static List<LottoTicket> buyManualLottoTickets(final List<LottoNumbers> lottoNumbers) {
        return lottoNumbers.stream()
                .map(LottoTicket::init)
                .collect(Collectors.toList());
    }

    private static LottoTicket generateAuto() {
        return LottoTicket.init(LottoNumbers.auto());
    }
}
