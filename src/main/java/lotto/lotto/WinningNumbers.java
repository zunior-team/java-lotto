package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers extends LottoTicket {

    private WinningNumbers(final LottoNumbers lottoNumbers) {
        super(lottoNumbers);
    }

    public static WinningNumbers init(final LottoNumbers lottoNumbers) {
        return new WinningNumbers(lottoNumbers);
    }

    public static WinningNumbers init(final List<Integer> lottoNumbers) {
        validate(lottoNumbers);

        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new WinningNumbers(LottoNumbers.init(numbers));
    }

    private static void validate(List<Integer> lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("Lotto number list is null");
        }
    }
}
