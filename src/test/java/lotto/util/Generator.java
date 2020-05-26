package lotto.util;

import lotto.lotto.LottoTicket;
import lotto.lotto.LottoTickets;
import lotto.lotto.WinningNumbers;
import lotto.money.Money;
import lotto.money.PaymentInfo;
import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.number.LottoNumbers.NUM_OF_LOTTO_NUM;

public class Generator {

    private Generator() {}

    public static List<LottoNumber> lottoNumberList(final int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList());
    }

    public static LottoNumbers lottoNumbers(final int... numbers) {
        return LottoNumbers.init(lottoNumberList(numbers));
    }

    public static LottoTicket lottoTicket(final int... numbers) {
        return LottoTicket.init(lottoNumbers(numbers));
    }

    public static WinningNumbers winningLotto(final int... numbers) {
        if (numbers.length < NUM_OF_LOTTO_NUM + 1) {
            throw new IllegalArgumentException("Winning lotto numbers should exact " + NUM_OF_LOTTO_NUM + 1);
        }

        int[] numberArray = numbers.clone();

        return WinningNumbers.init(
                lottoNumbers(Arrays.copyOf(numberArray, NUM_OF_LOTTO_NUM)),
                LottoNumber.of(numberArray[NUM_OF_LOTTO_NUM])
        );
    }

    public static LottoTickets lottoTickets(final LottoTicket... lottoTickets) {
        return LottoTickets.init(Arrays.asList(lottoTickets));
    }

    public static PaymentInfo paymentInfo(final int money) {
        Money payment = Money.of(money);

        return PaymentInfo.init(payment, Collections.emptyList());
    }
}
