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
