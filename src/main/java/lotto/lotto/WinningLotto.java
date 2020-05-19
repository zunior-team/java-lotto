package lotto.lotto;

import lotto.number.LottoNumbers;

public class WinningLotto extends LottoTicket {

    private WinningLotto(final LottoNumbers lottoNumbers) {
        super(lottoNumbers);
    }

    public static WinningLotto init(final LottoNumbers lottoNumbers) {
        return new WinningLotto(lottoNumbers);
    }
}
