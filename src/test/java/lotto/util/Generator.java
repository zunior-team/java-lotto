package lotto.util;

import lotto.number.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Generator {

    private Generator() {}

    public static List<LottoNumber> lottoNumberList(final String... strs) {
        return Arrays.stream(strs)
                .map(Integer::parseInt)
                .map(LottoNumber::new)
                .collect(Collectors.toList());
    }
}
