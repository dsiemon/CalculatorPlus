package des.calculator.calculatorPlus;

import java.util.ArrayList;

public class SystemFunctionNode extends ExpressionNode {
	private String name;
	private ArrayList<ExpressionNode> parameters;

	public SystemFunctionNode(String name){
		this.name = name;
		parameters = new ArrayList<ExpressionNode>();
	}
	
	public void addParameter(ExpressionNode node){
		parameters.add(node);
	}
	
	@Override
	public void prune() throws SyntaxError{
		for(int i = 0; i < parameters.size(); i++){
			ExpressionNode p = parameters.get(i);
			
			if(p.isConstant()){
				parameters.set(i,new LeafNode(p.evaluate()));
			}
			else{
				p.prune();
			}
		}
		
		pruned = true;
	}

	@Override
	public double evaluate() throws SyntaxError {
		double rtn = 0;


		if(state.isSystemFunction(name)){

			double[] params = new double[parameters.size()];
			
			// set up the parameters as local vars
			for(int i = 0; i < parameters.size(); i++){
				params[i] = parameters.get(i).evaluate();
			}
			
			for(int i = 0; i < params.length; i++){
				state.setLocalVariable("sf" + String.valueOf(i), params[i]);
			}
			
			rtn = state.callSystemFunction(name);
		}
		
		return rtn;
	}

	@Override
	/**
	 * in order to be constant the func and all params must be constant
	 */
	protected boolean isConstant() {
		boolean rtn = true;
		

		if(state.isSystemFunction(name)){
			
			// check the params
			for(int i = 0; i < parameters.size() && rtn; i++){
				rtn = parameters.get(i).isConstant();
			}
			
		}
		return rtn;
	}
	
	public String getName(){
		return name;
	}
	@Override
	public String print() {
		String rtn = "$" + name + "(";
		
		for(int i = 0; i < parameters.size(); i++){
			rtn += parameters.get(i).print();
			
			if(i + 1 != parameters.size()){
				rtn += ",";
			}
		}
		
		rtn += ")";
		return rtn;
	}
}
