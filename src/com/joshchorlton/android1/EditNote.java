package com.joshchorlton.android1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class EditNote extends Activity {

	private boolean save= true;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_note);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public void onStart(){
		super.onStart();
		Intent intent= getIntent();
		final EditText titleet= (EditText)findViewById(R.id.editText1);
		titleet.setText(intent.getStringExtra("title"));
		final EditText noteet= (EditText)findViewById(R.id.editText2);
		try{
			FileInputStream f= openFileInput(intent.getStringExtra("title"));
			String temp= "";
			char c= (char)f.read();
			int length= f.available()+1;
			for(int i= 0; i<length; i++){
				temp= temp+c;
				c= (char)f.read();
			}
			noteet.setText(temp);
			f.close();
		}catch(Exception e){}
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_edit_note, menu);
		return true;
	}

	public void delete(MenuItem m){
		final EditText titleet= (EditText)findViewById(R.id.editText1);
		String title= titleet.getText().toString();
		deleteFile(title);
		save= false;
		finish();
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
