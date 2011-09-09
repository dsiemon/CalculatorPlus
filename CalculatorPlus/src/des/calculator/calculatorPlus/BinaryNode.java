package des.calculator.calculatorPlus;

public class BinaryNode extends ExpressionNode {
	
	private ExpressionNode left;
	private ExpressionNode right;
	
	private BinaryOperator operation;
	public BinaryNode(ExpressionNode left, BinaryOperator operation, ExpressionNode right){
		this.left = left;
		this.right = right;
		this.operation = operation;
	}
	
	@Override
	public double evaluate() throws SyntaxError {
		return this.operation.evaluate(left.evaluate(), right.evaluate());
	}

	@Override
	public void prune() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isConstant() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String print() {
		
		return "(" + left.print() + operation.getSign() + right.print() + ")";
	}

}
