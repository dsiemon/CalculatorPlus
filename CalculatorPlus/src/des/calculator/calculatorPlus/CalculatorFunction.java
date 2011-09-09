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

public class CalculatorFunction {
	private String name;
	private String code;
	private ExpressionNode compiledCode;
	private boolean constantState;
	
	public CalculatorFunction(String name, String code, ExpressionNode compiledCode){
		this.name = name;
		this.code = code;
		this.compiledCode = compiledCode;
		this.constantState = this.compiledCode.isConstant();
	}
	
	public boolean isConstant(){
		return this.constantState;
	}

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public ExpressionNode getCompiledCode() {
		return compiledCode;
	}
	
}
