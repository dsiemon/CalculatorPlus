/*******************************************************************************
 * Copyright 2011 Douglas Siemon
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
