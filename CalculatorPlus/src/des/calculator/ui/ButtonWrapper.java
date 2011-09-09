package des.calculator.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import 	android.graphics.PorterDuff;
public class ButtonWrapper {
	
	public enum InputType{
		CHAR(0xFFC6FFC6),
		NUM(0xFF8BB9EB),
		TRIG(0xFFFFC4C4),
		OP(0xFF616FE9),
		PAREN(0xFFF3F3F3);
		
		private int color;
		private InputType(int c){
			color = c;
		}
		public static InputType getType(String name){
			if(name == null){
				return CHAR;
			}
			else if(name.equalsIgnoreCase("char")){
				return CHAR;
			}
			else if(name.equalsIgnoreCase("num")){
				return NUM;
			}
			else if(name.equalsIgnoreCase("trig")){
				return TRIG;
			}
			else if(name.equalsIgnoreCase("op")){
				return OP;
			}
			else if(name.equalsIgnoreCase("paren")){
				return PAREN;
			}
			return CHAR;
		}
		
		public int getColor(){
			return color;
		}
	}
	public static final int MAX_STATES = 5;
	private int state;
	
	private String[] displayValues;
	private String[] inputValues;
	private InputType[] inputType;
	
	private Button button;
	 
	public ButtonWrapper(String[] display, String[] input, InputType[] types){
		assert display.length == input.length && input.length > 0;
		
		state = 0;
		this.displayValues = display;
		this.inputValues = input;
		inputType = types;
		
		this.button = new Button(CalculatorActivity.context);
		this.button.setText(displayValues[state]);
		button.getBackground().setColorFilter(inputType[state].getColor(), PorterDuff.Mode.MULTIPLY);
		
		this.button.setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	CalculatorActivity.inputManager.insert(inputValues[state]);
    	    }
    	  });
	}
	
	public int getState(){
		return state;
	}
	public void setState(int state){
		if(state >= 0 && state < displayValues.length){
			this.state = state;
			this.button.setText(displayValues[state]);
			button.getBackground().setColorFilter(inputType[state].getColor(), PorterDuff.Mode.MULTIPLY);

		}
	}
	public Button getButton(){
		return button;
	}
}
