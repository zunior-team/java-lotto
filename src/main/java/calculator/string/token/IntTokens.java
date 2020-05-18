package calculator.string.token;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class IntTokens {
    private final List<Integer> tokens;

    private IntTokens(final String[] tokens) {
        validate(tokens);

        this.tokens = parseInt(tokens);
        validIsAllPositive();
    }

    private void validate(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            throw new IllegalArgumentException("String token is null");
        }
    }

    public static IntTokens init(final String[] tokens) {
        return new IntTokens(tokens);
    }

    public int calculate() {
        return tokens.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private List<Integer> parseInt(final String[] tokens) {
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validIsAllPositive() {
        boolean isAllPositive = tokens.stream()
                .allMatch(integer -> integer >= 0);

        if (!isAllPositive) {
            throw new IllegalArgumentException("Negative value has been detected");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntTokens anotherIntTokens = (IntTokens) o;
        return Objects.equals(tokens, anotherIntTokens.tokens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokens);
    }
}
