package des.calculator.calculatorPlus;

public class VariableNode extends ExpressionNode {
	private String name;
	
	public VariableNode(String name){
		this.name = name;
	}
	@Override
	public void prune() {
		pruned = true;
	}

	@Override
	public double evaluate() throws SyntaxError {
		return state.getVariable(this.name);
	}

	@Override
	protected boolean isConstant() {
		return false;
	}
	@Override
	public String print() {
		return name;
	}

}
