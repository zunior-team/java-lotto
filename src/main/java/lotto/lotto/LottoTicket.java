package lotto.lotto;

import lotto.number.LottoNumbers;

import java.util.List;

public class LottoTicket {
    private final LottoNumbers lottoNumbers;

    public LottoTicket(LottoNumbers lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validate(LottoNumbers lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("LottoNumbers is null");
        }
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.toInts();
    }
}
