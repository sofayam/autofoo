package com.example.libcore;



import com.example.libcommon.NestedMap;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class CoreService extends Service implements Callout{
	
	static final String TAG = "com.example.libcore.CoreService";
	Core core;
	
	public CoreService() {
	}

	// ----------------- L i f e c y c l e
	@Override
	public void onCreate(){
		Log.i(TAG,"created service");
		core = Core.revive();
		core.setCallout(this);
	}
	public void onDestroy(){
		Log.i(TAG,"destroyed service");
		core.persist();
	}
	
	// ----------------- D i s p a t c h   f r o m   i n t e n t s
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String actionName = intent.getAction();
		Bundle extras = intent.getExtras();
		String commandName = extras.getString("command");
		
		Log.i(TAG,"got intent with actionName:" + actionName + " commandName: " + commandName);
		

		return Service.START_STICKY;
	}
	
	
	
	// ----------------- C a l l o u t
	
	// TODO Intent building and sending utility here
	
	@Override
	public void showProgress(int progress) {
		// TODO Auto-generated method stub
		// Create suitably named intent with the right extras
		// send it
	}
	
	public void sendConfig(NestedMap config) {
		// TODO send config to GUI;
	}
	
	
	
	// ----------------- U n u s e d 
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}


}
