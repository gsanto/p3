package com.example.p3;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ToDoListApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		//Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
		Parse.initialize(this, "07imvo8rmxGxACo6ebKW5djyRuJgtqDgyDigVpSP", "yhO0bceqGdSevrkoNui7u3gYXE4evFafrHVg2z1j"); 
	        

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		// Optionally enable public read access.
		// defaultACL.setPublicReadAccess(true);
		ParseACL.setDefaultACL(defaultACL, true);
	}

}
