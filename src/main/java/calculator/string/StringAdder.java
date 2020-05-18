package calculator.string;

import calculator.string.interpret.Interpreter;
import calculator.string.token.IntTokens;

public class StringAdder {
    public static int calculate(final String str) {
        IntTokens intTokens = Interpreter.interpret(str);

        return intTokens.calculate();
    }
}
