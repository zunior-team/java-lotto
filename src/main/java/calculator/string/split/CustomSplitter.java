package calculator.string.split;

public class CustomSplitter extends DefaultSplitter {
    private final String customSplitPattern;

    private CustomSplitter(final String customSplitters) {
        this.customSplitPattern = "[" + DEFAULT_SPLITTERS + customSplitters + "]";
    }

    public static CustomSplitter init(final String customSplitters) {
        return new CustomSplitter(customSplitters);
    }

    @Override
    public String[] split(final String str) {
        return str.split(customSplitPattern);
    }
}
