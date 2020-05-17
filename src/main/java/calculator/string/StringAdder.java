package calculator.string;

import calculator.string.split.DefaultSplitter;
import calculator.string.split.Splitter;

import java.util.Arrays;

public class StringAdder {
    public static int calculate(final String str) {
        return Arrays.stream(new DefaultSplitter().split(str))
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
