package com.example.xdevcore;


import com.example.libcommon.Constants;
import com.example.libcommon.Constants.DisplayCommand;
import com.example.libcommon.Constants.CoreCommand;
import com.example.libcommon.NestedMap;
import com.example.libcommon.NestedMapContainer;


import com.example.libcore.CoreService;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;


public class MainActivity extends Activity {

	static final String TAG = "com.example.xdevcode.MainActivity";
	Activity me = this;
	private ProgressBar prog;
	Spinner command;
	EditText category;
	EditText nowPlaying;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setupInterface();
	}
	protected void onResume() {
		Log.i(TAG,"!!!resuming");
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.displayIntent);
		registerReceiver(bReceiver, filter);
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private BroadcastReceiver bReceiver = new BroadcastReceiver() {
		@Override

		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(Constants.displayIntent)) {
				Bundle extras = intent.getExtras();

				if (extras != null) {

					DisplayCommand command = (DisplayCommand) intent.getSerializableExtra("command");
					Log.i(TAG, "got command: " + command);

					switch (command) {
					case PROGRESS: {
						Log.i(TAG, "progress *********************");
						int progress = extras.getInt("percent",0);
						prog.setProgress(progress);
						break;
					}
					case NOWPLAYING: {
						String cat = extras.getString("category", "None");
						String playing = extras.getString("title","None");
						Log.i(TAG, "title: " + playing + " with cat: " + cat);
						nowPlaying.setText(playing);
						category.setText(cat);
						break;

					}
					case SETCONFIG: {
						NestedMap config = ((NestedMapContainer)intent.getExtras().
								get("config")).unpack();
						Log.i(TAG, "got config " + config);
						break;
					}


					default: 	
						Log.i(TAG, "unrecognised command");
					}
				}
			}
		}
	};


	

	private void setupInterface () {

		
		prog = (ProgressBar)findViewById(R.id.progress);
		prog.setMax(100);
		
		command = (Spinner) findViewById(R.id.command);
		command.setAdapter(
				new ArrayAdapter<CoreCommand>(
						this, 
						android.R.layout.simple_spinner_item, 
						CoreCommand.values()));
		
		
		Button doitButton = (Button) findViewById(R.id.doit);
		doitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				CoreCommand selected = (CoreCommand) command.getSelectedItem();
				Log.i(TAG, "Sending command: " + selected);
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("command", selected);
				
				if (selected == CoreCommand.ADDCONFIG) {

					intent.putExtra("key","categories:folk:selected");
					intent.putExtra("val", "true");
					startService(intent);
					intent.putExtra("key","categories:folk:weight");
					intent.putExtra("val", "4");
					startService(intent);					
					
				} else { 

					startService(intent);
				
				}
				
			}
		});
		
		nowPlaying = (EditText)findViewById(R.id.nowPlaying);
		category = (EditText)findViewById(R.id.category);
		
	}


}
