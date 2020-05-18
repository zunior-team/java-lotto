package calculator.string.split;

import calculator.string.token.IntTokens;
import calculator.string.util.StringUtil;

public class CustomSplitter extends DefaultSplitter {
    private final String customSplitPattern;

    private CustomSplitter(final String customSplitters) {
        validate(customSplitters);

        this.customSplitPattern = "[" + DEFAULT_SPLITTERS + customSplitters + "]";
    }

    private void validate(String customSplitters) {
        if (StringUtil.isEmpty(customSplitters)) {
            throw new IllegalArgumentException("Custom splitter can't be a null or empty");
        }
    }

    public static CustomSplitter init(final String customSplitters) {
        return new CustomSplitter(customSplitters);
    }

    @Override
    public IntTokens split(final String str) {
        return IntTokens.init(str.split(customSplitPattern));
    }
}
