package calculator.string.interpret;

import calculator.string.split.CustomSplitter;
import calculator.string.split.DefaultSplitter;
import calculator.string.split.Splitter;
import calculator.string.token.IntTokens;

public class Interpreter {
    private static final Splitter DEFAULT_SPLITTER = new DefaultSplitter();

    public static IntTokens interpret(final String expStr) {
        Expression expression = Expression.match(expStr);

        if (expression.isCustom()) {
            CustomSplitter customSplitter = CustomSplitter.init(expression.extractCustomSplitter());

            return customSplitter.split(expression.extractExpression());
        }

        return DEFAULT_SPLITTER.split(expression.origin());
    }
}
