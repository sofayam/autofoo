package com.example.libcore;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class CoreService extends Service implements Callout{
	
	Core core;
	
	public CoreService() {
	}

	// ----------------- L i f e c y c l e
	@Override
	public void onCreate(){
		Log.i("foo","created service");
		core = Core.revive();
		core.setCallout(this);
	}
	public void onDestroy(){
		Log.i("foo","destroyed service");
		core.persist();
	}
	// ----------------- D i s p a t c h 
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		Bundle extras = intent.getExtras();
		String source = extras.getString("source","none");

		return Service.START_STICKY;
	}
	
	// ----------------- C a l l o u t
	
	@Override
	public void showProgress(int progress) {
		// TODO Auto-generated method stub
		// Create suitably named intent with the right extras
		// send it
	}
	
	// ----------------- U n u s e d 
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}


}
