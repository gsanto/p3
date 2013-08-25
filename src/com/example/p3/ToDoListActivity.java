package com.example.p3;

import java.util.List;

import android.app.Dialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;


public class ToDoListActivity extends ListActivity {
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;

	public static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	public static int value33 = 0;
	
	public List<ParseObject> todos;
	public List<ParseObject> bett1;
	
	//public String[] zStrings = todos.subList(1, 6);//Cheeses.sCheeseStrings;
	private Dialog progressDialog;
	private int i;
	private int xx;
	
	public class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		// Override this method to do custom remote calls
		protected Void doInBackground(Void... params) {
			// Gets the current list of todos in sorted order
			ParseQuery query = new ParseQuery("Todo");
			query.orderByDescending("_created_at");

			try {
				todos = query.find();
			} catch (ParseException e) {

			}
			//return null;
			ParseQuery query2 = new ParseQuery("bett1");
			query2.orderByDescending("_created_at");

			try {
				bett1 = query2.find();
			} catch (ParseException e) {

			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			ToDoListActivity.this.progressDialog = ProgressDialog.show(ToDoListActivity.this, "",
					"Data Loading...", true);
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Void... values) {

			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(Void result) {
			// Put the list of todos into the list view
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(ToDoListActivity.this,
					R.layout.todo_row);
			
			i= 1;
			for (ParseObject todo : todos) {
				adapter.add((String) todo.get("name"));//createdAt"));
				if (i< 8 ){
				C2.zStrings[i]=  todo.getString("name");
				}
				i = i + 1;
			}
			Log.d("array todo list", "count: " + i);
			Log.e("array size list", "size: " + bett1.size());
			i = 0;
			for (ParseObject bettrecord : bett1) {
				xx = bett1.size();
				//if (i< 8 ){
					
				C2.zDataInizio[i]=  bettrecord.getString("autore") +" date " + bettrecord.getDate("datainizio").toString();
				//}
				i = i + 1;
				Log.e("size date", "xx: " + i);
			}
			Log.d("array date", "count: " + i);
			C2.zDataInizio[2]= C2.zStrings[2];
			//for (i= 0; i < 5; i++) {
				for (int i = 0; i < bett1.size(); i++) {
				//adapter.add((String) todo.get("name"));
				//Cheeses.zStrings[i]= (String) todo.get("name");
				//i = i + 1;
					Log.e("size date", "size: " + i);
			}
			setListAdapter(adapter);
			ToDoListActivity.this.progressDialog.dismiss();
			TextView empty = (TextView) findViewById(android.R.id.empty);
			empty.setVisibility(View.VISIBLE);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		TextView empty = (TextView) findViewById(android.R.id.empty);
		empty.setVisibility(View.INVISIBLE);

		new RemoteDataTask().execute();
		registerForContextMenu(getListView());
	}

	private void createTodo() {
		Intent i = new Intent(this, CreateTodo.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (intent == null) {
			return;
		}
		final Bundle extras = intent.getExtras();

		switch (requestCode) {
		case ACTIVITY_CREATE:
			new RemoteDataTask() {
				protected Void doInBackground(Void... params) {
					String name = extras.getString("name");
					value33 = value33 +1;
					ParseObject todo = new ParseObject("Todo");
					todo.put("name", name);
					todo.put("value2", value33);
					try {
						todo.save();
					} catch (ParseException e) {
					}

					super.doInBackground();
					return null;
				}
			}.execute();
			break;
		case ACTIVITY_EDIT:
			// Edit the remote object
			final ParseObject todo;
			todo = todos.get(extras.getInt("position"));
			todo.put("name", extras.getString("name"));
			todo.put("value3", 55);
			new RemoteDataTask() {
				protected Void doInBackground(Void... params) {
					try {
						todo.save();
					} catch (ParseException e) {
					}
					super.doInBackground();
					return null;
				}
			}.execute();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, R.string.menu_insert);
		return result;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

			// Delete the remote object
			final ParseObject todo = todos.get(info.position);

			new RemoteDataTask() {
				protected Void doInBackground(Void... params) {
					try {
						todo.delete();
					} catch (ParseException e) {
					}
					super.doInBackground();
					return null;
				}
			}.execute();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createTodo();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, CreateTodo.class);

		i.putExtra("name", todos.get(position).getString("name").toString());
		i.putExtra("position", position);
		startActivityForResult(i, ACTIVITY_EDIT);
	}

}