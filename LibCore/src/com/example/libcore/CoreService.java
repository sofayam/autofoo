package com.example.libcore;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class CoreService extends Service {
	public CoreService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate(){
		Log.i("foo","created service");
	}
	public void onDestroy(){
		Log.i("foo","destroyed service");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Bundle extras = intent.getExtras();
		String source = extras.getString("source","none");

		return Service.START_STICKY;
	}
}
