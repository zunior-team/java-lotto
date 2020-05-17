package calculator.string.split;

public class DefaultSplitter implements Splitter {
    protected static final String DEFAULT_SPLITTERS = ",:";
    private static final String DEFAULT_SPLIT_PATTERN = "[,:]";

    @Override
    public String[] split(final String str) {
        return str.split(DEFAULT_SPLIT_PATTERN);
    }
}
