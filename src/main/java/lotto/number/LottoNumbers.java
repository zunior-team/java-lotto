package lotto.number;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoNumbers {
    static final int NUM_OF_LOTTO_NUM = 6;

    private final Set<LottoNumber> lottoNumbers;

    private LottoNumbers(final List<LottoNumber> lottoNumbers) {
        validate(lottoNumbers);

        this.lottoNumbers = new LinkedHashSet<>(lottoNumbers);
    }

    private void validate(final List<LottoNumber> lottoNumbers) {
        if (lottoNumbers == null || lottoNumbers.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("Lotto number must be a " + NUM_OF_LOTTO_NUM);
        }

        checkDuplication(lottoNumbers);
    }

    private void checkDuplication(final List<LottoNumber> lottoNumbers) {
        Set<LottoNumber> numberSet = new HashSet<>(lottoNumbers);

        if (numberSet.size() < NUM_OF_LOTTO_NUM) {
            throw new IllegalArgumentException("There is number duplication");
        }
    }

    public static LottoNumbers init(final List<LottoNumber> lottoNumbers) {
        return new LottoNumbers(lottoNumbers);
    }

    public static LottoNumbers auto() {
        return new LottoNumbers(LottoNumberGenerator.generate());
    }

    public int matchCount(final LottoNumbers anotherLottoNumbers) {
        return Math.toIntExact(
                lottoNumbers.stream()
                .filter(anotherLottoNumbers::contains)
                .count()
        );
    }

    private boolean contains(final LottoNumber lottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber::equals);
    }

    public List<Integer> toInts() {
        return lottoNumbers.stream()
                .map(LottoNumber::toInt)
                .collect(Collectors.toList());
    }
}
