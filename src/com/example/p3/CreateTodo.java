package com.example.p3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateTodo extends Activity {

	private EditText nameText;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.contacts_list);
		//setTitle(R.string.create_todo);

		nameText = (EditText) findViewById(R.id.TextView01);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String name = extras.getString("name");
			position = extras.getInt("position");

			if (name != null) {
				nameText.setText(name);
			}
		}

		Button confirmButton = (Button) findViewById(R.id.button2);
				confirmButton.setOnClickListener(new View.OnClickListener() {
				public void onClick(View view) {
				Bundle bundle = new Bundle();
				bundle.putString("name", nameText.getText().toString());
				bundle.putInt("position", position);

				Intent intent = new Intent();
				intent.putExtras(bundle);
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

}
