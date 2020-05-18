package lotto.lotto;

import java.util.List;

public class LottoTicket {
    private static final int NUM_OF_LOTTO_NUM = 6;

    private final List<Integer> lottoNumbers;

    public LottoTicket(List<Integer> lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validate(List<Integer> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("Lotto number is null or insufficient, needs " + NUM_OF_LOTTO_NUM);
        }
    }
}
