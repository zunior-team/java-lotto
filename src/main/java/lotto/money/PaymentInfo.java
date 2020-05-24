package lotto.money;

import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

import static lotto.lotto.LottoSeller.PRICE_OF_A_TICKET;

public class PaymentInfo {
    private final Money payment;
    private final List<LottoNumbers> lottoNumbers;

    private PaymentInfo(final Money payment, final List<List<Integer>> lottoNumbers) {
        validate(payment, lottoNumbers);
        checkAffordable(payment, lottoNumbers);

        this.payment = payment;
        this.lottoNumbers = lottoNumbers.stream()
                .map(LottoNumbers::manual)
                .collect(Collectors.toList());
    }

    private void checkAffordable(Money payment, List<List<Integer>> lottoNumbers) {
        if (payment.getAffordableCount(PRICE_OF_A_TICKET) < lottoNumbers.size()) {
            throw new IllegalArgumentException("Manual lotto count over affordable ticket count");
        }
    }

    private void validate(final Money payment, final List<List<Integer>> lottoNumbers) {
        if (payment == null || lottoNumbers == null) {
            throw new IllegalArgumentException("Payment or manual lotto numbers is null");
        }
    }

    public static PaymentInfo init(final Money payment, final List<List<Integer>> lottoNumbers) {
        return new PaymentInfo(payment, lottoNumbers);
    }

    public Money getPayment() {
        return payment;
    }

    public List<LottoNumbers> getLottoNumbers() {
        return lottoNumbers;
    }

    public int getNoOfManualLotto() {
        return lottoNumbers.size();
    }
}
