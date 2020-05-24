package lotto.util;

import lotto.lotto.LottoTicket;
import lotto.lotto.LottoTickets;
import lotto.lotto.WinningNumbers;
import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.Arrays;
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
        int[] numberArray = numbers.clone();

        return WinningNumbers.init(
                lottoNumbers(Arrays.copyOf(numberArray, NUM_OF_LOTTO_NUM)),
                LottoNumber.of(numberArray[NUM_OF_LOTTO_NUM])
        );
    }

    public static LottoTickets lottoTickets(final LottoTicket... lottoTickets) {
        return LottoTickets.init(Arrays.asList(lottoTickets));
    }
}
