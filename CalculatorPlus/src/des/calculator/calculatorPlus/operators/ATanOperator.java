package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.SyntaxError;
import des.calculator.calculatorPlus.UnaryOperator;

public class ATanOperator extends UnaryOperator {

	@Override
	public double evaluate(double value) throws SyntaxError {
		return Math.atan(value);
	}

	@Override
	public String getSign() {
		return "atan";
	}

}
