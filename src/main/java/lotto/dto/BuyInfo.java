package lotto.dto;

import lotto.lotto.Lotto;

import java.util.List;

public class BuyInfo {
    private final int noOfManualLotto;
    private final List<LottoTicketDto> lottoTickets;

    private BuyInfo(final Lotto lotto) {
        validate(lotto);

        this.noOfManualLotto = lotto.getNoOfManualLotto();
        this.lottoTickets = lotto.getLottoTickets();
    }

    private void validate(Lotto lotto) {
        if (lotto == null) {
            throw new IllegalArgumentException("Lotto can't be a null");
        }
    }

    public static BuyInfo of(final Lotto lotto) {
        return new BuyInfo(lotto);
    }

    public int getNoOfManualLotto() {
        return noOfManualLotto;
    }

    public int getNoOfAutoLotto() {
        return lottoTickets.size() - noOfManualLotto;
    }

    public List<LottoTicketDto> getLottoTickets() {
        return lottoTickets;
    }
}
