package lotto.number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    static final int NUM_OF_LOTTO_NUM = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("Lotto number must be a " + NUM_OF_LOTTO_NUM);
        }

        duplicateCheck(lottoNumbers);
    }

    private void duplicateCheck(final List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numberSet = new HashSet<>(lottoNumbers);

        if (numberSet.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("There is number duplication");
        }
    }

    public static LottoNumbers init(final List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }
    public static LottoNumbers auto() {
        return new LottoNumbers(LottoNumberGenerator.generate());
    }

    public List<Integer> toInts() {
        return lottoNumbers.stream()
                .map(LottoNumber::toInt)
                .collect(Collectors.toList());
    }
}
