package com.example.xdevcore;


import com.example.libcommon.Constants;
import com.example.libcommon.Constants.DisplayCommand;
import com.example.libcommon.Constants.CoreCommand;

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
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

	static final String TAG = "com.example.xdevcode.MainActivity";
	Activity me = this;
	private ProgressBar prog;
	private int progInt = 0;
	
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
					default: 
						Log.i(TAG, "unrecognised command");
					}
				}
			}
		}
	};
	
	

	private void setupInterface () {


		Button startServiceButton = (Button) findViewById(R.id.start);
		startServiceButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "starting Service");
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("command", CoreCommand.START);
				startService(intent);

			}
		});
		Button startPlayButton = (Button) findViewById(R.id.play);
		startPlayButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "starting to PLAY");
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("command", CoreCommand.PLAY);
				startService(intent);

			}
		});
		Button pauseButton = (Button) findViewById(R.id.pause);
		pauseButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "starting to PAUSE");
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("command", CoreCommand.PAUSE);
				startService(intent);

			}
		});
		Button resumeButton = (Button) findViewById(R.id.resume);
		resumeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "RESUMing play");
				Intent intent = new Intent(me,CoreService.class);
				intent.putExtra("command", CoreCommand.RESUME);
				startService(intent);

			}
		});
		
		prog = (ProgressBar)findViewById(R.id.progress);
		prog.setMax(100);
	}


}
