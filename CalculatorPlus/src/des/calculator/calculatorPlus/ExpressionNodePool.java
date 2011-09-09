package des.calculator.calculatorPlus;

public class ExpressionNodePool {
	
	private ExpressionNodePool(){
		
	}
	
	public static ExpressionNodePool getInstance(){
		if(instance == null){
			instance = new ExpressionNodePool();
		}
		
		return instance;
	}
	
	private static ExpressionNodePool instance;
}
