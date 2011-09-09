package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.BinaryOperator;
import des.calculator.calculatorPlus.SyntaxError;

public class RootOperator extends BinaryOperator {

	@Override
	public double evaluate(double left, double right) throws SyntaxError {
		// TODO Auto-generated method stub
		return Math.pow(right, 1.0/left);
	}
	@Override
	public String getSign() {
		return "\u221A";
	}
}
