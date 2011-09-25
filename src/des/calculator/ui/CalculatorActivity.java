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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.android.calculator.ui.R;
import com.android.calculator.ui.R.id;
import com.android.calculator.ui.R.layout;
import com.android.calculator.ui.R.xml;

import des.calculator.calculatorPlus.ArithmeticParser;
import des.calculator.calculatorPlus.CalculatorParser;
import des.calculator.calculatorPlus.CalculatorState;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends Activity {

	public static CalculatorState state;
	public static CalculatorParser parser;
	
	public static ButtonManager buttonManager;
	public static CalculatorInputManager inputManager;
	public static CalculatorActivity context;
	public static final int SYSTEM_BUTTONS = 8;
	public Button systemButtons[];

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.calculator);
        parser = new ArithmeticParser(); 
        state = CalculatorState.getInstance();
        
        this.loadCalculatorState();
        this.setupInput();
          
        
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	try {
    		//save state
    		OutputStreamWriter out = new OutputStreamWriter(openFileOutput("state.xml",0), "UTF-8");
    		
    		state.saveState(out);
    		out.flush();
    		out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    @Override
    protected void onResume() {
        super.onResume();
        
        
    }
    public void loadCalculatorState(){
    	try {
	    	XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
	        //factory.setNamespaceAware(true);
	        XmlPullParser xpp = factory.newPullParser();
			xpp.setInput(this.openFileInput("state.xml"), "UTF-8");
			
			state.loadState(xpp,parser);
	        state.loadSystemFunctions();
	        state.loadSystemVariables();
	        
		} catch (FileNotFoundException e) {
			// ignore if file not found
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void setupInput(){
    	
    	

    	((EditText)this.findViewById(R.id.CalculatorInput)).setInputType(InputType.TYPE_NULL); 
    	((TextView)this.findViewById(R.id.CalculatorHistory)).setMovementMethod(new ScrollingMovementMethod());

		inputManager = new CalculatorInputManager((EditText)this.findViewById(R.id.CalculatorInput),(TextView)this.findViewById(R.id.CalculatorHistory));

    	// setup system buttons
    	this.setupSystemButtons();
    	
    	this.buttonManager = new ButtonManager(this.getResources().getXml(R.xml.buttons));


    	
    }
   
    
    public void setupSystemButtons(){
    	systemButtons = new Button[SYSTEM_BUTTONS];
    	
    	

    	systemButtons[0] = (Button)this.findViewById(R.id.s0);
    	systemButtons[0].setText("del");
    	systemButtons[0].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	CalculatorActivity.inputManager.delete();
    	    }
    	  });
    	
    	systemButtons[1] = (Button)this.findViewById(R.id.s1);
    	systemButtons[1].setText("clr");
    	systemButtons[1].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	CalculatorActivity.inputManager.clear();
    	    }
    	  }); 
    	
    	systemButtons[2] = (Button)this.findViewById(R.id.s2);
    	systemButtons[2].setText("2nd");
    	systemButtons[2].getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
    	systemButtons[2].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	buttonManager.toggleSecond();
    	    	if(buttonManager.getState() == 2){
    	    		systemButtons[3].getBackground().setColorFilter(0xFFF000F0, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	else{
    	    		systemButtons[3].getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	if(buttonManager.getState() == 1){
    	    		systemButtons[2].getBackground().setColorFilter(0xFFF000F0, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	else{
    	    		systemButtons[2].getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    }
    	  });
    	systemButtons[3] = (Button)this.findViewById(R.id.s3);
    	systemButtons[3].setText("A");
    	systemButtons[3].getBackground().setColorFilter(0xFFF0F0F0, PorterDuff.Mode.MULTIPLY);
    	systemButtons[3].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	
    	    	buttonManager.toggleAlpha();
    	    	if(buttonManager.getState() == 2){
    	    		systemButtons[3].getBackground().setColorFilter(0xFFF000F0, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	else{
    	    		systemButtons[3].getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	
    	    	if(buttonManager.getState() == 1){
    	    		systemButtons[2].getBackground().setColorFilter(0xFFF000F0, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    	else{
    	    		systemButtons[2].getBackground().setColorFilter(0xFFFFFFFF, PorterDuff.Mode.MULTIPLY);
    	    	}
    	    }
    	  });

    	systemButtons[5] = (Button)this.findViewById(R.id.s5);
    	//systemButtons[3].setText("up");
    	systemButtons[5].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	CalculatorActivity.inputManager.cursorUp();
    	    }
    	  });
    	systemButtons[6] = (Button)this.findViewById(R.id.s6);
    	//systemButtons[4].setText("down");
    	systemButtons[6].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	CalculatorActivity.inputManager.cursorDown();
    	    }
    	  });  
 
    	systemButtons[7] = (Button)this.findViewById(R.id.s7);
    	//systemButtons[5].setText("ent");
    	systemButtons[7].setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	String rtn = CalculatorActivity.parser.evaluateExpression(CalculatorActivity.inputManager.getInput());
    	    	
    	    	CalculatorActivity.inputManager.setInput(rtn);
    	    	CalculatorActivity.inputManager.setHistory();
    	    }
    	  });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//    	boolean result = false;
//    	if (keyCode == KeyEvent.KEYCODE_BACK) {
//    		
//    	} else if (keyCode == KeyEvent.KEYCODE_MENU) {
//    	
//    	}
//    	
//    	return result;
//    }
}
