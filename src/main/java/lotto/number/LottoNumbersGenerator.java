package lotto.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.number.LottoNumber.MAX_NUM_OF_LOTTO;
import static lotto.number.LottoNumber.MIN_NUM_OF_LOTTO;
import static lotto.number.LottoNumbers.NUM_OF_LOTTO_NUM;

public class LottoNumbersGenerator {
    static final int BASE_INDEX = 0;
    private static final List<LottoNumber> LOTTO_NUMBERS =
            IntStream.rangeClosed(MIN_NUM_OF_LOTTO, MAX_NUM_OF_LOTTO)
                    .mapToObj(LottoNumber::of)
                    .collect(Collectors.toList());

    private LottoNumbersGenerator() {}

    public static List<LottoNumber> generate() {
        List<LottoNumber> numberPool = createNumberPool();

        Collections.shuffle(numberPool);

        return Collections.unmodifiableList(extractLottoNumbers(numberPool));
    }

    private static List<LottoNumber> extractLottoNumbers(final List<LottoNumber> numberPool) {
        List<LottoNumber> newLottoNumbers = numberPool.subList(BASE_INDEX, NUM_OF_LOTTO_NUM);

        Collections.sort(newLottoNumbers);

        return newLottoNumbers;
    }

    private static List<LottoNumber> createNumberPool() {
        return new ArrayList<>(LOTTO_NUMBERS);
    }
}
