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
import des.calculator.ui.R;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import android.widget.TabHost.TabSpec;
import android.widget.TableRow.LayoutParams;


/**
 * This demonstrates how you can implement switching between the tabs of a
 * TabHost through fragments.  It uses a trick (see the code below) to allow
 * the tabs to switch between fragments instead of simple views.
 */
public class MenuTabActivity extends FragmentActivity {
    TabHost tabHost;
    private TableLayout historyTable;
    
    
    private CalculatorState state = CalculatorState.getInstance();
//    TabManager mTabManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.menu);
    	tabHost=(TabHost)findViewById(R.id.tabhost);
    	tabHost.setup();

    	TabSpec spec1=tabHost.newTabSpec("History");
    	spec1.setContent(R.id.historyTab);
    	spec1.setIndicator("History");

    	TabSpec spec2=tabHost.newTabSpec("Funtions");
    	spec2.setIndicator("Funtions");
    	spec2.setContent(R.id.functionTab);

    	TabSpec spec3=tabHost.newTabSpec("Variables");
    	spec3.setIndicator("Variables");
    	spec3.setContent(R.id.variableTab);

    	tabHost.addTab(spec1);
    	tabHost.addTab(spec2);
    	tabHost.addTab(spec3);
    	
    	// create the history tab content
    	this.historyTable = (TableLayout)this.findViewById(R.id.historyTable);
        Button clearButton = (Button)this.findViewById(R.id.clearHistory);
        clearButton.setOnClickListener(new OnClickListener() {
    	    @Override
    	    public void onClick(View v) {
    	    	state.clearHistory();
    	    	buildHistoryTable();
    	    }
    	  });
        this.buildHistoryTable();

        if (savedInstanceState != null) {
            tabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tab", tabHost.getCurrentTabTag());
    }
    
    private void buildHistoryTable(){
    	final int historySize = state.getHistoryLength();

    	TableRow currentRow = null;
    	// clear the table in case it has already been loaded.
    	historyTable.removeAllViews();
    	// for each history entry
    	for(int i = historySize - 1; i >= 0; i--){
    		// create the new row
    		currentRow = new TableRow(this);
    		//TableLayout.LayoutParams rowLayout = new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
    		
    		//rowLayout.setMargins(2, 2, 2, 2);
    		//currentRow.setLayoutParams(rowLayout);
    		//currentRow.setBackgroundColor(0xFF282828);
    		currentRow.setBackgroundResource(R.layout.rowlayout);
    		historyTable.addView(currentRow,new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    		
    		// add the history info
    		TextView entry = new TextView(this);
    		entry.setText(state.getInputHistory(i));
    		entry.setGravity(Gravity.LEFT);
    		entry.setTextSize(20);
    		entry.setTextColor(0xFFFFFFFF);
    		
    		TextView answer = new TextView(this);
    		answer.setText(state.getAnsHistory(i));
    		answer.setGravity(Gravity.RIGHT);
    		answer.setWidth(30);
    		answer.setTextSize(20);
    		answer.setTextColor(0xFFFFFFFF);
    		
    		// create layout params for the text views
    		TableRow.LayoutParams textLayout = new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    		textLayout.setMargins(4, 1, 4, 1);

    		currentRow.addView(entry,textLayout);
    		currentRow.addView(answer,textLayout);
    	} 
    }
}