/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.p3;

import com.parse.ParseException;
import com.parse.ParseObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A list view example where the 
 * data for the list comes from an array of strings.
 */
public class List1 extends ListActivity {
	public String[] mStrings = C2.zDataInizio;//Strings;//sCheeseStrings;
    public String user1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setListAdapter(new ArrayAdapter<String>(this,
        		android.R.layout.simple_selectable_list_item, mStrings));
        //android.R.layout.simple_list_item_1,mStrings));
        getListView().setTextFilterEnabled(true);
     

 }


    public void showZetaDialog(View v) {
    //	public void onListItemClick(ListView l, View v, int position, long id) {
	
	Toast.makeText(this, "beta: " + mStrings.length, Toast.LENGTH_LONG).show();
	//digitalClock1.setBackgroundColor(121);
    final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
    alertBuilder.setTitle("Sciamano time "+ System.currentTimeMillis());
    
    String n = "sss"; //((XSDateTime)rs.first()).stringvalue(); println(n);


    //alertBuilder.setMessage("Id: " + mCursor.getPosition());
    final EditText input = new EditText(this);
    alertBuilder.setView(input);
    //alertBuilder.setMessage("iiiii");
    //input.setText(SUGGESTED_URL);

    alertBuilder.setPositiveButton("Ok start!", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dlg, int whichButton) {
            // Send an intent with the URL of the song to play. This is expected by
            
            //chronometer1.stop();
            //final id/textView1.textview == "aaa"
            //startService(i);
            //startService(new Intent(MusicService.ACTION_PLAY));
        }
    });
    alertBuilder.setNeutralButton("Save to remote", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dlg, int whichButton) {
        	
        	if ( LoginViewActivity.currentUser == null ) {
        		
        	    user1= "zetatest";
        	    
        	}else {
        		
        	    user1= LoginViewActivity.currentUser;
        	    
        	}
				
					int value3 =  3;
					int value4 =  3;
					int value5 =  5;
					ParseObject Field1 = new ParseObject("Field1");
					
					Field1.put("value3", value3);
					Field1.put("value4", value4);
					Field1.put("value5", value5);
					Field1.put("userLog", user1);
					try {
						Field1.save();
					} catch (ParseException e) {
					}
					
					//RemoteDataTask..ToDoListActivity.doInBackground();
			
        	//mTextView1.setText("parva");
        	//mTextView1.setTextColor(RESULT_OK); 
      }
    });

    alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
    	//chronometer1.stop();
    	public void onClick(DialogInterface dlg, int whichButton) {
    		//chronometer1.stop();
    		//mTextView1.setTextColor(RESULT_CANCELED);
    	}
    });

    alertBuilder.show();
}

    @Override 
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    	Toast.makeText(this, "beta: " + position+ " id: "+ id, Toast.LENGTH_LONG).show();
    }
}