package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLotto extends LottoTicket {

    private WinningLotto(final LottoNumbers lottoNumbers) {
        super(lottoNumbers);
    }

    public static WinningLotto init(final LottoNumbers lottoNumbers) {
        return new WinningLotto(lottoNumbers);
    }

    public static WinningLotto init(final List<Integer> lottoNumbers) {
        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new WinningLotto(LottoNumbers.init(numbers));
    }
}
