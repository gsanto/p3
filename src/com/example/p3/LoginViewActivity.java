/*
 * Copyright 2012 Amandeep Grewal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.p3;

import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;

public class LoginViewActivity extends Activity {
	public   int countlog;
	public static String currentUser;
	public String date1;
  /**
   * Parse app client key
   */
  protected String clientKey = "yhO0bceqGdSevrkoNui7u3gYXE4evFafrHVg2z1j";

  /**
   * Parse app application ID
   */
  protected String applicationId = "07imvo8rmxGxACo6ebKW5djyRuJgtqDgyDigVpSP" ;
 // Parse.initialize(this, "07imvo8rmxGxACo6ebKW5djyRuJgtqDgyDigVpSP", "yhO0bceqGdSevrkoNui7u3gYXE4evFafrHVg2z1j"); 
  
  /**
   * Twitter app consumer key
   */
  protected String twitterConsumerKey = "xxx";

  /**
   * Twitter app consumer secret
   */
  protected String twitterConsumerSecret = "xxx";

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

  private LoginListener listener;
  
  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Parse.initialize(this, applicationId, clientKey);
    ParseTwitterUtils.initialize(twitterConsumerKey, twitterConsumerSecret);

    setContentView(R.layout.info);

    ((Button) findViewById(R.id.login)).getBackground().setColorFilter(Color.parseColor("#50B52D"), Mode.SRC_ATOP);
    ((Button) findViewById(R.id.facebook)).getBackground().setColorFilter(Color.parseColor("#005EFF"), Mode.SRC_IN);
    ((Button) findViewById(R.id.twitter)).getBackground().setColorFilter(Color.parseColor("#0095FF"), Mode.SRC_ATOP);
  }

  public void setListener(com.example.p3.LoginViewActivity.LoginListener listener) {
    this.listener = listener;
  }

  public void twitterCallback(View v) {
    ParseTwitterUtils.logIn(this, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        if (user == null) {
          listener.onError("twitter", err);
        } else if (user.isNew()) {
          listener.onSignup("twitter", user);
        } else {
          listener.onSignin("twitter", user);
        }
      }
    });
  }

  public void facebookCallback(View v) {
    ParseTwitterUtils.logIn(this, new LogInCallback() {
      @Override
      public void done(ParseUser user, ParseException err) {
        if (user == null) {
          listener.onError("facebook", err);
        } else if (user.isNew()) {
          listener.onSignup("facebook", user);
        } else {
          listener.onSignin("facebook", user);
        }
      }
    });
  }

  private String getLoginUserName() {
    return ((EditText) findViewById(R.id.username)).getText().toString();
  }

  private String getLoginPwd() {
    return ((EditText) findViewById(R.id.pwd)).getText().toString();
  }

  public void loginCallback(View v) {
	    final ProgressDialog dialog = ProgressDialog.show(this, "",
	    		getLoginUserName()+" Login ...", true);
	    ParseUser.logInInBackground(getLoginUserName(), getLoginPwd(), new LogInCallback() {
	      public void done(ParseUser user, ParseException e) {
	        dialog.dismiss();
	        user.increment("CountLog");
	        user.saveInBackground();
	        //date1 = 66;
	        //date1 = user.getDate("updatedAt").toString();
	        //ParseUser.create("aaaProduct000");
	        ParseObject testObject = new ParseObject("Field1");
	        countlog = 553;
	        if (user != null) {
	        	currentUser = getLoginUserName();
	    		testObject.put("log1", "ok user 333 bar");
	    		testObject.put("userLog", getLoginUserName());
	    		testObject.put("countLog", countlog);
	    		Log.w("Login", "User logged: "+ currentUser + " access " + user.getInt("CountLog"));
	        	
	    		//testObject.put("log2", 1);
	          //listener.onSignin("native", user);
	        } else {
	        	Log.d("Login", "No such user");
	    		testObject.put("log1", "no such user");
	    		testObject.put("log2", "no such user");
	          //listener.onError("native", e);
	        }
	        Toast.makeText(getApplicationContext(), "Last access " + user.getInt("CountLog"), Toast.LENGTH_LONG).show();
	        
	      testObject.saveInBackground();  
	      }
	    });
	  }
}