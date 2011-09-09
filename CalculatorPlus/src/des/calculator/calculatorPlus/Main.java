package des.calculator.calculatorPlus;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArithmeticParser parser = new ArithmeticParser();
		
		System.out.println(parser.evaluateExpression("x=2*3+2"));
		System.out.println(parser.evaluateExpression("2*3+2+x"));
	}

}
