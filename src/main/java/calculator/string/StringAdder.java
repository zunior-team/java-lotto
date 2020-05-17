package calculator.string;

import java.util.Arrays;

public class StringAdder {
    public static int calculate(final String str) {
        String[] tokens = str.split("[,:]");

        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
