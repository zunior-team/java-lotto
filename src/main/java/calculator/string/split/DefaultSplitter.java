package calculator.string.split;

import calculator.string.token.IntTokens;

public class DefaultSplitter implements Splitter {
    protected static final String DEFAULT_SPLITTERS = ",:";
    private static final String DEFAULT_SPLIT_PATTERN = "[,:]";

    @Override
    public IntTokens split(final String str) {
        return IntTokens.init(str.split(DEFAULT_SPLIT_PATTERN));
    }
}
