package lotto.number;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {
    static final int NUM_OF_LOTTO_NUM = 6;
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<LottoNumber> lottoNumbers) {
        if(lottoNumbers == null || lottoNumbers.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("Lotto number must be a " + NUM_OF_LOTTO_NUM);
        }

        duplicateCheck(lottoNumbers);
    }

    private void duplicateCheck(List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numberSet = new HashSet<>(lottoNumbers);

        if(numberSet.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("There is number duplication");
        }
    }
}
