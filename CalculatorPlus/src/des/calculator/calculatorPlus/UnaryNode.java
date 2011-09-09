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

public class UnaryNode extends ExpressionNode {

	private ExpressionNode child;
	private UnaryOperator operation;
	
	public UnaryNode(ExpressionNode child, UnaryOperator operation){
		this.child = child;
		this.operation = operation;
	}
	
	@Override
	public double evaluate() throws SyntaxError {
		return this.operation.evaluate(child.evaluate());
	}

	public ExpressionNode getChild() {
		return child;
	}

	public void setChild(ExpressionNode child) {
		this.child = child;
	}

	public UnaryOperator getOperation() {
		return operation;
	}

	public void setOperation(UnaryOperator operation) {
		this.operation = operation;
	}

	@Override
	public void prune() throws SyntaxError{
		if(this.child.isConstant()){
			child = new LeafNode(this.child.evaluate());
		}
		else{
			this.child.prune();
		}
		
		pruned = true;
	}

	@Override
	protected boolean isConstant() {
		return this.child.isConstant();
	}

	@Override
	public String print() {
		return this.operation.getSign() + "(" + this.child.print() + ")";
	}

}
