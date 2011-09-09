package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.SyntaxError;
import des.calculator.calculatorPlus.UnaryOperator;

public class LogOperator extends UnaryOperator {

	@Override
	public double evaluate(double value) throws SyntaxError {
		return Math.log10(value);
	}

	@Override
	public String getSign() {
		return "log";
	}

}
