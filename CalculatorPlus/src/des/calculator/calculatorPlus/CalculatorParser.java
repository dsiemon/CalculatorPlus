package des.calculator.calculatorPlus;

import des.calculator.gen.ParseException;
import des.calculator.gen.TokenMgrError;

public abstract class CalculatorParser {
	public static final CalculatorState state = CalculatorState.getInstance();
	
	public abstract ExpressionNode parseExpression(String expression) throws SyntaxError, NumberFormatException, ParseException, TokenMgrError;
	public abstract String evaluateExpression(String expression);
}
