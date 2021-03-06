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
