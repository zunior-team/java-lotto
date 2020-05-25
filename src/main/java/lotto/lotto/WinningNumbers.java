package lotto.lotto;

import lotto.number.LottoNumber;
import lotto.number.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers {

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusNumber;

    private WinningNumbers(final LottoNumbers lottoNumbers, final LottoNumber bonusNumber) {
        validate(lottoNumbers);
        validate(bonusNumber);
        this.lottoNumbers = lottoNumbers;
        this.bonusNumber = bonusNumber;
        checkDuplication(bonusNumber);
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

    private static <T> void validate(T object) {
        if (object == null) {
            throw new IllegalArgumentException("Lotto number or number list is null");
        }
    }

    public boolean isBonusMatch(LottoNumbers lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }

    public LottoNumbers getLottoNumber() {
        return lottoNumbers;
    }
}
