package des.calculator.calculatorPlus;




import des.calculator.gen.CalculatorPlusParser;
import des.calculator.gen.ParseException;
import des.calculator.gen.TokenMgrError;


public class ArithmeticParser extends CalculatorParser {

	@Override
	public ExpressionNode parseExpression(String expression) throws SyntaxError, NumberFormatException, ParseException, TokenMgrError {


		
		CalculatorPlusParser parser = CalculatorPlusParser.getParser(expression);

		parser.parse();

		return parser.root;
	}

	@Override
	public String evaluateExpression(String expression) {
		String rtn = "";

		CalculatorPlusParser parser = CalculatorPlusParser.getParser(expression);
		try{
			parser.parse();
			
			if(parser.isFunc){
				// evaluate to make sure the syntax is good
				parser.root.evaluate();
				CalculatorFunction func = new CalculatorFunction(parser.name, expression, parser.root);
				state.setFunction(parser.name, func);
				state.setPreviousValues(0.0);
				rtn = parser.name + ":=" + parser.root.print();
			}
			else if(parser.isVar){
				double value = parser.root.evaluate();
				state.setVariable(parser.name, value);
				state.setPreviousValues(value);
				rtn = parser.name + "=" + value;
			}
			else{
				double value = parser.root.evaluate();
				state.setPreviousValues(value);
				rtn = String.valueOf(value);
			}
			
			
			
		}
		catch(Exception e){
			// add a zero in for syntax errors
			state.setPreviousValues(0.0);
			rtn = "error";//e.getClass() + ": " + e.getMessage();
		}
		
		CalculatorState.getInstance().addHistory(expression, rtn);
		
		return rtn;
	}
}
