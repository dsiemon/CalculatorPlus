package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.BinaryOperator;
import des.calculator.calculatorPlus.SyntaxError;

public class ExponentOperator extends BinaryOperator {

	@Override
	public double evaluate(double left, double right) throws SyntaxError {
		return Math.pow(left, right);
	}
	@Override
	public String getSign() {
		return "^";
	}
}
