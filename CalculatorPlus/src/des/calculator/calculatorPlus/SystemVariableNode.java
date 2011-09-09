package des.calculator.calculatorPlus;

public class SystemVariableNode extends ExpressionNode {
	private String name;
	
	public SystemVariableNode(String name){
		this.name = name;
	}
	@Override
	public void prune() {
		pruned=true;
	}

	@Override
	public double evaluate() throws SyntaxError {
		return state.getSystemVariable(name);
	}

	@Override
	protected boolean isConstant() {
		return true;
	}
	
	public String getName(){
		return name;
	}
	@Override
	public String print() {
		return "$" + name;
	}
}
