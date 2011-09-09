/*******************************************************************************
 * Copyright 2011 Douglas Siemon
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
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
