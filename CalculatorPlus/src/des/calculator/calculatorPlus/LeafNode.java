package des.calculator.calculatorPlus;

public class LeafNode extends ExpressionNode {
	
	private double value;
	
	public LeafNode(double value){
		this.value = value;
	}
	
	
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public double evaluate() {
		
		return value;
	}
	
	@Override
	public void prune() {
		pruned = true;
	}

	@Override
	protected boolean isConstant() {
		return true;
	}



	@Override
	public String print() {
		return String.valueOf(this.value);
	}

}
