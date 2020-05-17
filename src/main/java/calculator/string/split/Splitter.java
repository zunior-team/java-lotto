package calculator.string.split;

public class Splitter {
    private static final String DEFAULT_SPLITTERS = ",:";
    private static final String DEFAULT_SPLIT_PATTERN = "[,:]";


    public static String[] split(String str) {
        return str.split(DEFAULT_SPLIT_PATTERN);
    }
}
