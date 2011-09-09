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

import des.calculator.gen.ParseException;
import des.calculator.gen.TokenMgrError;

public abstract class CalculatorParser {
	public static final CalculatorState state = CalculatorState.getInstance();
	
	public abstract ExpressionNode parseExpression(String expression) throws SyntaxError, NumberFormatException, ParseException, TokenMgrError;
	public abstract String evaluateExpression(String expression);
}
