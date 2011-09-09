package des.calculator.calculatorPlus.operators;

import des.calculator.calculatorPlus.SyntaxError;
import des.calculator.calculatorPlus.UnaryOperator;

public class FloorOperator extends UnaryOperator {

	@Override
	public double evaluate(double value) throws SyntaxError {
		return Math.floor(value);
	}

	@Override
	public String getSign() {
		return "flr";
	}

}
