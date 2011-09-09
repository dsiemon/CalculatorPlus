package des.calculator.calculatorPlus;

public abstract class ExpressionNode {
	public static final ExpressionNodePool nodePool = ExpressionNodePool.getInstance();
	public static final CalculatorState state = CalculatorState.getInstance();
	protected boolean pruned;
	
	public abstract void prune() throws SyntaxError;
	public abstract double evaluate() throws SyntaxError;
	public abstract String print();
	/**
	 * an implementation of this should return true if the evaluated
	 * @return
	 */
	protected abstract boolean isConstant();
	
	
	
	public boolean isPruned(){
		return pruned;
	}
}
