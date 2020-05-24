package lotto;

import lotto.dto.BuyInfo;
import lotto.match.MatchResult;
import lotto.lotto.Lotto;
import lotto.lotto.LottoSeller;
import lotto.lotto.WinningNumbers;
import lotto.money.Money;
import lotto.money.PaymentInfo;
import lotto.view.ConsoleInput;
import lotto.view.ConsoleOutput;

import java.util.List;

public class LottoGame {
    public static void main(String[] args) {
        Money payment = Money.of(ConsoleInput.inputPayment());
        List<List<Integer>> manualLottos = ConsoleInput.inputNumberOfManualLotto();

        Lotto lotto = LottoSeller.buy(PaymentInfo.init(payment, manualLottos));
        ConsoleOutput.showLottoTickets(BuyInfo.of(lotto));

        List<Integer> winningNumbers = ConsoleInput.inputWinningLottoNumber();
        int bonusNumber = ConsoleInput.inputBonusNumber();
        MatchResult matchResult = lotto.match(WinningNumbers.init(winningNumbers, bonusNumber));

        ConsoleOutput.showMatchResult(matchResult);
    }
}
