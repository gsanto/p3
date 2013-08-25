package com.example.p3;

import android.app.ListActivity;
import com.example.p3.ToDoListActivity.RemoteDataTask;
import com.parse.ParseException;
import com.parse.ParseObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class info extends ListActivity {
    final String SUGGESTED_URL = "http://cursi.salentovirtuale.com";
	public Cursor mCursor;
    private String[] mS1 = C1.sC1;
    private String user1;
	protected Object ToDoListActivity;
    
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.contacts_list);
        //setContentView(R.layout.photos_list);
        //setListAdapter(Adapters.loadCursorAdapter(this, R.xml.contacts,"q123"));
                

        // Query for all people contacts using the Contacts.People convenience class.
        // Put a managed wrapper around the retrieved cursor so we don't have to worry about
        // requerying or closing it as the activity changes state.
        mCursor = this.getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(mCursor);
        String[] mS2 = C2.zStrings;
        Log.w("people date", "count: " + People.CONTENT_URI);
        // Now create a new list adapter bound to the cursor.
        // SimpleListAdapter is designed for binding to a Cursor.
        ListAdapter adapter = new SimpleCursorAdapter(
                this, // Context.
                R.layout.list8_item ,  // Specify the row template to use (here, two columns bound to the two retrieved cursor rows).
                mCursor,                                              // Pass in the cursor to bind to.
                
                //new String[]{mS2[1] , C2.zStrings[mCursor.getPosition()-1], C2.zStrings[mCursor.getCount()-1] },           // Array of cursor columns to bind to.
                new String[] {People._ID , People.NAME, People._ID},           // Array of cursor columns to bind to.
                
                new int[] {R.id.text2, R.id.text1, R.id.TextView01});  // Parallel array of which template objects to bind to those columns.

        // Bind to our new adapter.
        setListAdapter(adapter);
    }
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
    	Toast.makeText(this, "beta: " + position+ " id: "+ id, Toast.LENGTH_LONG).show();
    }
    
	public void showZetaDialog(View v) {
		//Toast.makeText(this, "beta: " + mCursor.getCount() + " pos "+mCursor.getPosition(), Toast.LENGTH_LONG).show();
		//digitalClock1.setBackgroundColor(121);
	    final AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
	    alertBuilder.setTitle("Sciamano g time "+ System.nanoTime());//currentTimeMillis());
	    
	    String n = "sss"; //((XSDateTime)rs.first()).stringvalue(); println(n);
	
	
	    alertBuilder.setMessage("Id: " + mCursor.getPosition());
	    final EditText input = new EditText(this);
	    alertBuilder.setView(input);
	    input.setText(SUGGESTED_URL);
	
	    alertBuilder.setPositiveButton("Ok start!", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dlg, int whichButton) {
	            // Send an intent with the URL of the song to play. This is expected by
	            // MusicService.
	            //Intent i = new Intent(MusicService.ACTION_URL);
	            Uri uri = Uri.parse(input.getText().toString());
	            //i.setData(uri);
	            
	            //chronometer1.start();
	            for (long zi= 0; zi < 100; zi++) {
	                int a = 1;
	
	            }
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
					
						int value3 =  mCursor.getPosition();
						int value4 =  mCursor.getColumnCount();
						int value5 =  mCursor.getCount();
						ParseObject Field1 = new ParseObject("Field1");
						Field1.increment("countLog");//value5 = Field1.getInt("log2");
						//value5 = value5 +1;
						//Field1.put("log2", value5);
						Field1.put("value3", value3);
						Field1.put("value4", value4);
						Field1.put("value5", value5);
						Field1.put("userLog", user1);
						//Field1.increment("log2");
						try {
							Field1.save();
						} catch (ParseException e) {
						}
						
						//Field1.saveInBackground();
				
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
}
