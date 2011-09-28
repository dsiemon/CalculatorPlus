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
package des.calculator.calculatorPlus;

import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import des.calculator.ui.R;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Xml;

/**
 * This class maintains variable values and functions across different parsers
 */
public class CalculatorState {
	
	private static CalculatorState instance;
	
	HashMap<String,Double> variables;
	HashMap<String, Double> localVariables;
	HashMap<String, CalculatorFunction> functions;
	
	HashMap<String, Double> systemVariables;

	ArrayList<String> inputHistory;
	ArrayList<String> answerHistory;
	public static final int MAX_HISTORY = 20;
	public static final int MAX_VAR = 40;
	public static final int MAX_FUNC = 40;
	
	String currentInput;
	
	private CalculatorState(){
		variables = new HashMap<String, Double>(MAX_VAR);
		localVariables = new HashMap<String, Double>(MAX_VAR);
		functions = new HashMap<String, CalculatorFunction>(MAX_FUNC);
		
		systemVariables = new HashMap<String, Double>();
		
		inputHistory = new ArrayList<String>(MAX_HISTORY);
		answerHistory = new ArrayList<String>(MAX_HISTORY);
	}
	
	public void saveState(OutputStreamWriter out){
		XmlSerializer serializer = Xml.newSerializer();
		
	    try {
	        serializer.setOutput(out);
	        serializer.startDocument("UTF-8", true);
	        
	        serializer.startTag("", "state");
	        
	        serializer.startTag("", "var");
	        
	        Iterator<Entry<String,Double>> iter = this.variables.entrySet().iterator();
	        while(iter.hasNext()){
	        	Entry<String,Double> current = iter.next();
	        	serializer.startTag("", "v");
	        	serializer.attribute("", "id", current.getKey());
	        	serializer.attribute("", "value", current.getValue().toString());
	        	serializer.endTag("", "v");
	        }
	        serializer.endTag("", "var");
	        
	        serializer.startTag("", "func");
	        
	        Iterator<Entry<String,CalculatorFunction>> funcIter = this.functions.entrySet().iterator();
	        while(funcIter.hasNext()){
	        	Entry<String,CalculatorFunction> current = funcIter.next();
	        	serializer.startTag("", "f");
	        	serializer.attribute("", "id", current.getKey());
	        	serializer.attribute("", "value", current.getValue().getCode());
	        	serializer.endTag("", "f");
	        }
	        serializer.endTag("", "func");
	        
	        serializer.startTag("", "hist");
	        for(int i = 0; i < this.inputHistory.size(); i++){
	        	serializer.startTag("", "h");
	        	serializer.attribute("", "exp", this.inputHistory.get(i));
	        	serializer.attribute("", "ans", this.answerHistory.get(i));
	        	serializer.endTag("", "h");
	        }
	        serializer.endTag("", "hist");

	        serializer.endTag("", "state");
	        serializer.endDocument();
	        
	    } catch (Exception e) {
	        
	    } 
	}
	
	/**
	 * loads variables, functions and history, it will compile functions
	 * @param xpp
	 * @param parser
	 */
	public void loadState(XmlPullParser xpp, CalculatorParser parser){
    	try{
    		// prepare by clearing the state containers
    		variables.clear();
    		functions.clear();
    		inputHistory.clear();
    		answerHistory.clear();
    		
		    xpp.next();
		    int eventType = xpp.getEventType();
		    while (eventType != XmlPullParser.END_DOCUMENT)
		    {
		    	if(eventType == XmlPullParser.START_TAG){
		    		if(xpp.getName().compareTo("v") == 0){
		    			variables.put(xpp.getAttributeValue(null, "id"), Double.parseDouble(xpp.getAttributeValue(null, "value")));
		    		}
		    		else if(xpp.getName().compareTo("f") == 0){
		    			String code = xpp.getAttributeValue(null, "value");
		    			String name = xpp.getAttributeValue(null, "id");
		    			try{
		    				functions.put(name, new CalculatorFunction(name, code, parser.parseExpression(code)));
		    			}
		    			catch(Exception e){
		    				
		    			}
		    		}
		    		else if(xpp.getName().compareTo("h") == 0){
		    			String exp = xpp.getAttributeValue(null, "exp");
		    			String ans = xpp.getAttributeValue(null, "ans");
		    			
		    			if(exp == null) exp = "";
		    			if(ans == null) ans = "";
		    			
		    			inputHistory.add(exp);
		    			answerHistory.add(ans);
		    		}
		    	}

		    	eventType = xpp.next();
		    }
    	}
    	catch(Exception e){
    		// TODO add error handleing
		}
	}
	
	/**
	 * Lists variable names
	 * @return
	 */
	public String[] listVariables(){
		return (String[])this.variables.keySet().toArray();
	}
	
	/**
	 * Lists variable names
	 * @return
	 */
	public String[] listSystemVariables(){
		return (String[])this.systemVariables.keySet().toArray();
	}
	/**
	 * Lists function names
	 * @return
	 */
	public String[] listFuntions(){
		return (String[])this.functions.keySet().toArray();
	}
	/**
	 * Lists function names
	 * @return
	 */
	public String[] listSystemFuntions(){
		return new String[]{""};
	}
	public void loadSystemVariables(){
		this.systemVariables.put("e", Math.E);
		this.systemVariables.put("pi", Math.PI);
		
		int ansSize = this.answerHistory.size();
		
		for(int i = 0; i < 10; i++){
			if(i < ansSize){
				Double tmp = null;
				try{
					tmp = Double.valueOf(this.answerHistory.get(ansSize - (i+1)));
				}
				catch(Exception e){
					tmp = new Double(0);
				}
				this.systemVariables.put("p" + String.valueOf(i), tmp);
			}
			else{
				this.systemVariables.put("p" + String.valueOf(i), 0.0);
			}
		}

	}
	
	public void setPreviousValues(double value){
		this.systemVariables.put("p9", this.systemVariables.get("p8"));
		this.systemVariables.put("p8", this.systemVariables.get("p7"));
		this.systemVariables.put("p7", this.systemVariables.get("p6"));
		this.systemVariables.put("p6", this.systemVariables.get("p5"));
		this.systemVariables.put("p5", this.systemVariables.get("p4"));
		this.systemVariables.put("p4", this.systemVariables.get("p3"));
		this.systemVariables.put("p3", this.systemVariables.get("p2"));
		this.systemVariables.put("p2", this.systemVariables.get("p1"));
		this.systemVariables.put("p1", this.systemVariables.get("p0"));
		
		this.systemVariables.put("p0", value);
		this.systemVariables.put("p", value);
	}
	public void loadSystemFunctions(){
		
	}
	public int getHistoryLength(){
		return inputHistory.size();
	}
	public String getInputHistory(int i){
		return inputHistory.get(i);
	}
	public String getAnsHistory(int i){
		return answerHistory.get(i);
	}
	
	public void clearHistory(){
		answerHistory.clear();
		inputHistory.clear();
		
		this.systemVariables.put("p9", 0.0);
		this.systemVariables.put("p8", 0.0);
		this.systemVariables.put("p7", 0.0);
		this.systemVariables.put("p6", 0.0);
		this.systemVariables.put("p5", 0.0);
		this.systemVariables.put("p4", 0.0);
		this.systemVariables.put("p3", 0.0);
		this.systemVariables.put("p2", 0.0);
		this.systemVariables.put("p1", 0.0);
		
		this.systemVariables.put("p0", 0.0);
		this.systemVariables.put("p", 0.0);
	}
	public void addHistory(String exp, String ans){
		inputHistory.add(exp);
		answerHistory.add(ans);
		
		if(inputHistory.size() > MAX_HISTORY){
			inputHistory.remove(0);
			answerHistory.remove(0);
		}
	}
	
	public static CalculatorState getInstance(){
		if(instance == null){
			instance = new CalculatorState();
		}
		
		return instance;
	}
	public void setLocalVariable(String name, double value){
		localVariables.put(name, value);
	}	
	public void setVariable(String name, double value){
		variables.put(name, value);
	}
	public double getVariable(String name){
		double rtn = 0;
		
		if(localVariables.containsKey(name)){
			rtn = localVariables.get(name);
		}
		else if(variables.containsKey(name)){
			rtn = variables.get(name);
		}

		return rtn;
	}
	
	public double getSystemVariable(String name){
		double rtn = 0;
		
//		if(name.compareTo("p") == 0){
//			if(this.answerHistory.size() > 0){
//				try{
//					rtn = Double.parseDouble(this.answerHistory.get(this.answerHistory.size() - 1));
//				}
//				catch(Exception e){
//					
//				}
//			}
//		}
//		else 
			if(systemVariables.containsKey(name)){
			rtn = systemVariables.get(name);
		}

		return rtn;
	}
	public void setFunction(String name, CalculatorFunction function){
		this.functions.put(name, function);
	}
	public CalculatorFunction getFunction(String name){
		return this.functions.get(name);
	}
	
	public double callSystemFunction(String name){
		return 0;
	}
	public boolean isSystemFunction(String name){
		return false;
	}

}
