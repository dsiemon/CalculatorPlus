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
