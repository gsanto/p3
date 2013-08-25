package com.example.p3;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;



public class cActivity extends ListActivity {
public Cursor mCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        // We'll define a custom screen layout here (the one shown above), but
        // typically, you could just use the standard ListActivity layout.
        setContentView(R.layout.contacts_list);

        // Query for all people contacts using the Contacts.People convenience class.
        // Put a managed wrapper around the retrieved cursor so we don't have to worry about
        // requerying or closing it as the activity changes state.
        mCursor = this.getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(mCursor);
        String[] mS2 = C1.sC1;
        // Now create a new list adapter bound to the cursor.
        // SimpleListAdapter is designed for binding to a Cursor.
        ListAdapter adapter = new SimpleCursorAdapter(
                this, // Context.
                R.layout.list8_item ,  // Specify the row template to use (here, two columns bound to the two retrieved cursor rows).
                mCursor,                                              // Pass in the cursor to bind to.
                new String[] {People._ID , People.NAME, People._ID },           // Array of cursor columns to bind to.
                new int[] {R.id.text2, R.id.text1, R.id.TextView01});  // Parallel array of which template objects to bind to those columns.

        // Bind to our new adapter.
        setListAdapter(adapter);
    }
    private String[] mS1 = C1.sC1;
}
