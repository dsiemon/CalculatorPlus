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


import des.calculator.calculatorPlus.operators.ACosOperator;
import des.calculator.calculatorPlus.operators.ASinOperator;
import des.calculator.calculatorPlus.operators.ATanOperator;
import des.calculator.calculatorPlus.operators.AbsoluteValueOperator;
import des.calculator.calculatorPlus.operators.CeilingOperator;
import des.calculator.calculatorPlus.operators.CosOperator;
import des.calculator.calculatorPlus.operators.FloorOperator;
import des.calculator.calculatorPlus.operators.LNOperator;
import des.calculator.calculatorPlus.operators.LogOperator;
import des.calculator.calculatorPlus.operators.NegOperator;
import des.calculator.calculatorPlus.operators.SinOperator;
import des.calculator.calculatorPlus.operators.TanOperator;

public abstract class UnaryOperator {
	public abstract double evaluate(double value) throws SyntaxError;
	public abstract String getSign();
	
	public static UnaryOperator unaryFactory(String operation) throws SyntaxError{
		UnaryOperator rtn = null;
		
		if(operation.compareTo(negOperator.getSign()) == 0){
			rtn = negOperator;
		}
		else if(operation.compareTo(lnOperator.getSign()) == 0){
			rtn = lnOperator;
		}
		else if(operation.compareTo(ceilingOperator.getSign()) == 0){
			rtn = ceilingOperator;
		}
		else if(operation.compareTo(floorOperator.getSign()) == 0){
			rtn = floorOperator;
		}
		else if(operation.compareTo(logOperator.getSign()) == 0){
			rtn = logOperator;
		}
		else if(operation.compareTo(absOperator.getSign()) == 0){
			rtn = absOperator;
		}
		else if(operation.compareTo(sinOperator.getSign()) == 0){
			rtn = sinOperator;
		}
		else if(operation.compareTo(cosOperator.getSign()) == 0){
			rtn = cosOperator;
		}
		else if(operation.compareTo(tanOperator.getSign()) == 0){
			rtn = tanOperator;
		}
		else if(operation.compareTo(aSinOperator.getSign()) == 0){
			rtn = aSinOperator;
		}
		else if(operation.compareTo(aCosOperator.getSign()) == 0){
			rtn = aCosOperator;
		}
		else if(operation.compareTo(aTanOperator.getSign()) == 0){
			rtn = aTanOperator;
		}
		else{
			throw new SyntaxError("Unsupported unary operation: " + operation);
		}
		return rtn;
	}
	
	private static final SinOperator sinOperator = new SinOperator();
	private static final CosOperator cosOperator = new CosOperator();
	private static final TanOperator tanOperator = new TanOperator();
	private static final ASinOperator aSinOperator = new ASinOperator();
	private static final ACosOperator aCosOperator = new ACosOperator();
	private static final ATanOperator aTanOperator = new ATanOperator();
	
	private static final AbsoluteValueOperator absOperator = new AbsoluteValueOperator();
	private static final LogOperator logOperator = new LogOperator();
	private static final LNOperator lnOperator = new LNOperator();
	
	private static final FloorOperator floorOperator = new FloorOperator();
	private static final CeilingOperator ceilingOperator = new CeilingOperator();
	
	private static final NegOperator negOperator = new NegOperator();
}
