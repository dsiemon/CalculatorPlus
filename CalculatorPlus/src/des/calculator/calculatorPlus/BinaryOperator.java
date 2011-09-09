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
