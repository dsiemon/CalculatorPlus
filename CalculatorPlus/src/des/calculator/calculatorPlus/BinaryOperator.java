package des.calculator.calculatorPlus;



import des.calculator.calculatorPlus.operators.AdditionOperator;
import des.calculator.calculatorPlus.operators.DivisionOperator;
import des.calculator.calculatorPlus.operators.ExponentOperator;
import des.calculator.calculatorPlus.operators.MinusOperator;
import des.calculator.calculatorPlus.operators.ModulusOperator;
import des.calculator.calculatorPlus.operators.MultiplicationOperator;
import des.calculator.calculatorPlus.operators.RootOperator;


public abstract class BinaryOperator {
	public abstract double evaluate(double left, double right) throws SyntaxError;
	public abstract String getSign();
	
	public static BinaryOperator binaryFactory(String operation) throws SyntaxError{
		BinaryOperator rtn = null;
		
		if(operation.compareTo(addition.getSign()) == 0){
			rtn = addition;
		}
		else if(operation.compareTo(minus.getSign()) == 0){
			rtn = minus;
		}
		else if(operation.compareTo(division.getSign()) == 0){
			rtn = division;
		}
		else if(operation.compareTo(multiplication.getSign()) == 0){
			rtn = multiplication;
		}
		else if(operation.compareTo(modulus.getSign()) == 0){
			rtn = modulus;
		}
		else if(operation.compareTo(exponent.getSign()) == 0){
			rtn = exponent;
		}
		else if(operation.compareTo(root.getSign()) == 0){
			rtn = root;
		}
		else{
			throw new SyntaxError("unsupported binary operation: " + operation);
		}
		return rtn;
	}
	
	private static final AdditionOperator addition = new AdditionOperator();
	private static final MinusOperator minus = new MinusOperator();
	
	private static final DivisionOperator division = new DivisionOperator();	
	private static final MultiplicationOperator multiplication = new MultiplicationOperator();
	private static final ModulusOperator modulus = new ModulusOperator();
	
	private static final ExponentOperator exponent = new ExponentOperator();
	private static final RootOperator root = new RootOperator();
}
