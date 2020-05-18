package lotto.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.number.LottoNumber.MAX_NUM_OF_LOTTO_NUM;
import static lotto.number.LottoNumber.MIN_NUM_OF_LOTTO_NUM;
import static lotto.number.LottoNumbers.NUM_OF_LOTTO_NUM;

public class LottoNumberGenerator {
    static final int BASE_INDEX = 0;
    private static final List<LottoNumber> LOTTO_NUMBERS =
            IntStream.rangeClosed(MIN_NUM_OF_LOTTO_NUM, MAX_NUM_OF_LOTTO_NUM)
                    .mapToObj(LottoNumber::new)
                    .collect(Collectors.toList());

    private LottoNumberGenerator() {}

    public static List<LottoNumber> generate() {
        List<LottoNumber> numberPool = createNumberPool();

        Collections.shuffle(numberPool);

        return Collections.unmodifiableList(extractLottoNumbers(numberPool));
    }

    private static List<LottoNumber> extractLottoNumbers(List<LottoNumber> numberPool) {
        List<LottoNumber> newLottoNumbers = numberPool.subList(BASE_INDEX, NUM_OF_LOTTO_NUM);

        Collections.sort(newLottoNumbers);

        return newLottoNumbers;
    }

    private static List<LottoNumber> createNumberPool() {
        return new ArrayList<>(LOTTO_NUMBERS);
    }
}
