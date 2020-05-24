package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInput {
    private static final String SPLITTER = ",";
    private static final String INPUT_PAYMENT_STATEMENT = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER_STATEMENT = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_BALL_STATEMENT = "보너스 볼을 입력해 주세요.";

    private static final Scanner SCANNER = new Scanner(System.in);

    private ConsoleInput() {
    }

    public static int inputPayment() {
        System.out.println(INPUT_PAYMENT_STATEMENT);

        return receiveInt();
    }

    private static int receiveInt() {
        return Integer.parseInt(SCANNER.nextLine());
    }

    public static List<Integer> inputWinningLottoNumber() {
        System.out.println(INPUT_WINNING_NUMBER_STATEMENT);

        String input = SCANNER.nextLine();

        return Arrays.stream(input.split(SPLITTER))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int inputBonusNumber() {
        System.out.println(INPUT_BONUS_BALL_STATEMENT);

        return receiveInt();
    }
}
