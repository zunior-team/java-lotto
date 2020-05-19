package lotto.util;

import lotto.lotto.LottoTicket;
import lotto.lotto.LottoTickets;
import lotto.lotto.WinningLotto;
import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {

    private Generator() {}

    public static List<LottoNumber> lottoNumberList(final int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    public static LottoNumbers lottoNumbers(final int... numbers) {
        return LottoNumbers.init(lottoNumberList(numbers));
    }

    public static LottoTicket lottoTicket(final int... numbers) {
        return LottoTicket.init(lottoNumbers(numbers));
    }

    public static WinningLotto winningLotto(final int... numbers) {
        return WinningLotto.init(lottoNumbers(numbers));
    }

    public static LottoTickets lottoTickets(final LottoTicket... lottoTickets) {
        return LottoTickets.init(Arrays.asList(lottoTickets));
    }
}
