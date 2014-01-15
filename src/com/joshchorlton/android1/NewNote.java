package com.joshchorlton.android1;

import java.io.FileOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class NewNote extends Activity {
	private boolean save= true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_note);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onStart(){
		super.onStart();
	}

	@Override
	public void onResume(){
		super.onResume();
	}


	@Override
	public void onPause() {
		super.onPause();
		if(save){
			final EditText titleet= (EditText)findViewById(R.id.editText1);
			String title= titleet.getText().toString();
			final EditText noteet= (EditText)findViewById(R.id.editText2);
			String note= noteet.getText().toString();
			try{
				FileOutputStream f= openFileOutput(title, Context.MODE_PRIVATE);
				f.write(note.getBytes());
				f.close();
			}catch(Exception e){}
		}
	}

	@Override
	public void onStop(){
		super.onStop();
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
	}

	public void delete(MenuItem m){
		final EditText titleet= (EditText)findViewById(R.id.editText1);
		String title= titleet.getText().toString();
		deleteFile(title);
		save= false;
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_new_note, menu);
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
