package lotto.number;

import java.util.List;

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
    }
}
