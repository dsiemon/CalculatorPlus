package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.BinaryOperator;
import des.calculator.calculatorPlus.SyntaxError;

public class AdditionOperator extends BinaryOperator {

	@Override
	public double evaluate(double left, double right) throws SyntaxError {
		return left + right;
	}

	@Override
	public String getSign() {
		return "+";
	}

}