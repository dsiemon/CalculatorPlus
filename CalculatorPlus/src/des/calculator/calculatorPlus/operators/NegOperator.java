package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.SyntaxError;
import des.calculator.calculatorPlus.UnaryOperator;

public class NegOperator extends UnaryOperator {

	@Override
	public double evaluate(double value) throws SyntaxError {
		return -value;
	}

	@Override
	public String getSign() {
		return "-";
	}

}
