package com.example.xdevcore;

import com.example.libcore.CoreService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	static final String TAG = "com.example.xdevcode.MainActivity";
	Activity me = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupInterface();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setupInterface () {


		Button startServiceButton = (Button) findViewById(R.id.start);
		startServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "starting Service");
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("source", "main");
				startService(intent);

			}
		});
	}


}
