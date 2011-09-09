package des.calculator.ui;

import des.calculator.calculatorPlus.CalculatorState;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorInputManager {
	
	
	private EditText textInput;
	private TextView textHistory;
	CalculatorState state = CalculatorState.getInstance();

	private int lineNumber;
	String userText;
	 
	public CalculatorInputManager(EditText t,TextView h){
		this.textInput = t;
		this.textHistory = h;
		


		
		
		lineNumber = state.getHistoryLength();
		userText = "";
		
		this.setHistory();
	}
	public void delete(){
		if(this.textInput.getSelectionStart() > 0){
		this.textInput.getText().delete(this.textInput.getSelectionStart()-1, this.textInput.getSelectionStart());
		}
	}
	public void clear(){
		this.textInput.setText("");
	}

	public void cursorUp(){
		 
		if(lineNumber > 0){
			lineNumber--;
		}		
		if(lineNumber >= 0 || lineNumber < this.state.getHistoryLength()){

			this.setInput(state.getInputHistory(lineNumber));
			
		}

	}
	public void cursorDown(){
		if(lineNumber < state.getHistoryLength()){
			lineNumber++;
		}		
		
		if(lineNumber == this.state.getHistoryLength()){
			this.setInput("");
		}
		else if(lineNumber >= 0 || lineNumber < this.state.getHistoryLength()){
			this.setInput(state.getInputHistory(lineNumber));
		}
		
		

	}
	
	public void insert(String input){
		this.textInput.getText().insert(this.textInput.getSelectionStart(), input);
		this.lineNumber = state.getHistoryLength();
	}
	
	public String getInput(){
		String rtn = this.textInput.getText().toString();

		return rtn;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setHistory(){
		String history = "";
		
		for(int i = state.getHistoryLength() - 1; i >= 0; i--){
			history += state.getInputHistory(i) + "\n > " + state.getAnsHistory(i) + "\n";
		}
		
		this.textHistory.setText(history);
		
		this.lineNumber = state.getHistoryLength();
	}
	
	public void setInput(String input){	
		this.textInput.setText(input);
		this.textInput.setSelection(this.textInput.getText().length());
	}
}
