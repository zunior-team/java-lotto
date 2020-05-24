package lotto.lotto;

import lotto.number.LottoNumbers;
import lotto.prize.LottoPrize;

import java.util.List;

public class LottoTicket {
    protected final LottoNumbers lottoNumbers;

    protected LottoTicket(final LottoNumbers lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = lottoNumbers;
    }

    private void validate(final LottoNumbers lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("LottoNumbers is null");
        }
    }

    public static LottoTicket init(final LottoNumbers lottoNumbers) {
        return new LottoTicket(lottoNumbers);
    }

    public List<Integer> getNumbers() {
        return lottoNumbers.toInts();
    }

    public LottoPrize matchPrize(final WinningNumbers winningNumbers) {
        int matchCount = lottoNumbers.matchCount(winningNumbers.lottoNumbers);

        return LottoPrize.of(matchCount);
    }
}
