package des.calculator.ui;

import org.xmlpull.v1.XmlPullParser;

import com.android.calculator.ui.R;
import com.android.calculator.ui.R.id;

import des.calculator.calculatorPlus.CalculatorFunction;

import android.content.res.XmlResourceParser;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
public class ButtonManager {

	private ButtonWrapper[] buttons;
	private int rowWidth;
	private int currentState;
	
	public ButtonManager(XmlResourceParser xpp){
		currentState = 0;
		
		
    	try{ 
    		
		    xpp.next();
		    int eventType = xpp.getEventType();
		    
		    int buttonCount = 0;
		    
		    int stateCount = 0;
		    int currentState = 0;
		    String[] display = null;
		    String[] input= null;
		    ButtonWrapper.InputType[] type = null;
		    while (eventType != XmlPullParser.END_DOCUMENT)
		    {
		    	if(eventType == XmlPullParser.START_TAG){
		    		if(xpp.getName().compareTo("buttons") == 0){
		    			buttonCount = Integer.parseInt(xpp.getAttributeValue(null, "count"));
		    			buttons = new ButtonWrapper[buttonCount];
		    			
		    			rowWidth = Integer.parseInt(xpp.getAttributeValue(null, "rowWidth"));
		    			buttonCount = 0;
		    		}
		    		else if(xpp.getName().compareTo("button") == 0){
		    			stateCount = Integer.parseInt(xpp.getAttributeValue(null, "count"));
		    		    display = new String[stateCount];
		    		    input = new String[stateCount];
		    		    type = new ButtonWrapper.InputType[stateCount];
		    			currentState = 0;
		    		}
		    		else if(xpp.getName().compareTo("state") == 0){
		    			input[currentState] = xpp.getAttributeValue(null, "input");
		    			display[currentState] = xpp.getAttributeValue(null, "display");
		    			type[currentState] = ButtonWrapper.InputType.getType(xpp.getAttributeValue(null, "type"));
		    			currentState++;
		    		}
		    	} 
		    	else if(eventType == XmlPullParser.END_TAG){
		    		if(xpp.getName().compareTo("button") == 0){
		    			buttons[buttonCount] = new ButtonWrapper(display, input, type);
		    			buttonCount++;
		    		}
		    	}
		    	eventType = xpp.next();
		    }
    	}
    	catch(Exception e){
    		// TODO add error handleing
		}
    	
    	final CalculatorActivity context = CalculatorActivity.context;
    	// setup the button layout
    	TableLayout tl = (TableLayout)context.findViewById(R.id.ButtonTable);
    	TableRow currentRow = null;
    	// for each button
    	for(int i = 0; i < this.buttons.length; i++){
    		// get the current button
    		Button currentButton = buttons[i].getButton();
    		 
    		// start a new table row
    		if(i%this.rowWidth == 0){
    			currentRow = new TableRow(context);
    			currentRow.setLayoutParams(new TableLayout.LayoutParams(
                      LayoutParams.MATCH_PARENT,
                      LayoutParams.MATCH_PARENT));
		        tl.addView(currentRow,new TableLayout.LayoutParams(
	                      LayoutParams.MATCH_PARENT,
	                      LayoutParams.MATCH_PARENT));
    		}
    		
    		// add the button to the current row
    		// set layout
    		
    		currentButton.setLayoutParams(new LayoutParams(
                      LayoutParams.MATCH_PARENT,
                      LayoutParams.MATCH_PARENT));
    		// add 
    		currentRow.addView(currentButton);
    	} 
	}
	
	
	public void toggleSecond(){
		if(currentState != 1){
			currentState = 1;
		}
		else if(currentState == 1){
			currentState = 0;
		}
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i].setState(currentState);
		}
			
	}
	
	public void toggleAlpha(){
		if(currentState != 2){
			currentState = 2;
		}
		else if(currentState == 2){
			currentState = 0;
		}
		
		for(int i = 0; i < buttons.length; i++){
			buttons[i].setState(currentState);
		}
	}
	
	public int getState(){
		return currentState;
	}
}
