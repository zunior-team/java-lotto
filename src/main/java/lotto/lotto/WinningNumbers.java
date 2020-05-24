package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers extends LottoTicket {

    private final LottoNumber bonusNumber;

    private WinningNumbers(final LottoNumbers lottoNumbers, final LottoNumber bonusNumber) {
        super(lottoNumbers);

        validate(bonusNumber);
        checkDuplication(bonusNumber);
        this.bonusNumber = bonusNumber;
    }

    private void checkDuplication(final LottoNumber bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("Bonus number is already exist in winning lotto numbers");
        }
    }

    public static WinningNumbers init(final LottoNumbers lottoNumbers, final LottoNumber bonusNumber) {
        return new WinningNumbers(lottoNumbers, bonusNumber);
    }

    public static WinningNumbers init(final List<Integer> lottoNumbers, final int bonusNumber) {
        validate(lottoNumbers);

        List<LottoNumber> numbers = lottoNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList());

        return new WinningNumbers(LottoNumbers.init(numbers), LottoNumber.of(bonusNumber));
    }

    private static void validate(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Lotto number or number list is null");
        }
    }

    public boolean isBonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }
}
