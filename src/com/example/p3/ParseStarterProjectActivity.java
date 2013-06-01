package com.example.p3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;



import android.widget.Button;
import android.widget.EditText;


import com.parse.ParseTwitterUtils;





public class ParseStarterProjectActivity extends Activity {
	/**
	   * Listener for login events fired from the login view
	   */
	  public interface LoginListener {
	    /**
	     * Fired if new user created
	     *
	     * @param type One of "twitter", "facebook", or "native"
	     * @param user The user object for this particular user
	     */
	    public void onSignup(String type, ParseUser user);
	
	    /**
	     * Fired if existing user signed in
	     *
	     * @param type One of "twitter", "facebook", or "native"
	     * @param user The user object for this particular user
	     */
	    public void onSignin(String type, ParseUser user);
	
	    /**
	     * Fired if signin/signup process errored in any way
	     *
	     * @param type One of "twitter", "facebook", or "native"
	     * @param err  Error object
	     */
	    public void onError(String type, ParseException err);
	  }



	/** Called when the activity is first created. */
	
private LoginListener listener;	
	private String getLoginUserName() {
    return ((EditText) findViewById(R.id.username)).getText().toString();
  }

  private String getLoginPwd() {
    return ((EditText) findViewById(R.id.pwd)).getText().toString();
  }
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ParseAnalytics.trackAppOpened(getIntent());
	}

	
	
	public void signupCallback(View v) {
		    final ProgressDialog dialog = ProgressDialog.show(this, "",
		        "default Logging in ...", true);
		    ///Parse.initialize(this, "07imvo8rmxGxACo6ebKW5djyRuJgtqDgyDigVpSP", "yhO0bceqGdSevrkoNui7u3gYXE4evFafrHVg2z1j");
	
			//ParseUser.enableAutomaticUser();
			///ParseACL defaultACL = new ParseACL();
		    
			// If you would like all objects to be private by default, remove this line.
			///defaultACL.setPublicReadAccess(true);
			
			///ParseACL.setDefaultACL(defaultACL, true);
		    ParseUser user = new ParseUser();
		   
		
		user.setUsername("gsanto");
		user.setPassword("");
		user.setEmail("gsanto11@gmail.com");
		 
		// other fields can be set just like with ParseObject
		user.put("phone", "000-253-0000");
		user.put("dato1", "333-211-0011"); 
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
			  dialog.dismiss();
			  ParseObject testObject1 = new ParseObject("TestObject1");
		    if (e == null) {
		      // Hooray! Let them use the app now.
		    	 Log.d("Signup", "ok new user logged in 111.");
		    	 testObject1.put("sign", "ok new user");
		    } else {
		    	 Log.d("Signup", "user exist 111.");
		    	 //ParseObject testObject1 = new ParseObject("TestObject1");
		    	 testObject1.put("sign", "user exist");
		    	 
		      // Sign up didn't succeed. Look at the ParseException
		      // to figure out what went wrong
		    }
		    testObject1.saveInBackground();
		  }
		});
		ParseObject testObject = new ParseObject("TestObject");
		testObject.put("sign", "sign1");
		testObject.put("numero", "super 333 bar");
		testObject.put("foo", "zzz 333 bar");
		testObject.saveInBackground();
	  }



	public void loginCallback(View v) {
	    final ProgressDialog dialog = ProgressDialog.show(this, "",
	    		getLoginUserName()+" Login ...", true);
	    ParseUser.logInInBackground(getLoginUserName(), getLoginPwd(), new LogInCallback() {
	      public void done(ParseUser user, ParseException e) {
	        dialog.dismiss();
	        ParseObject testObject = new ParseObject("TestObject");
	        if (user != null) {
	        	Log.d("Login", "User logged in 111.");
	        	
	    		testObject.put("log1", "ok user 333 bar");
	    		testObject.put("log2", "ok user 333 bar");
	    		
	          //listener.onSignin("native", user);
	        } else {
	        	Log.d("Login", "No such user");
	    		testObject.put("log1", "no such user");
	    		testObject.put("log2", "no such user");
	          //listener.onError("native", e);
	        }
	      testObject.saveInBackground();  
	      }
	    });
	  }
}
