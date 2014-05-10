package com.example.libcore;




import com.example.libcommon.Constants;
import com.example.libcommon.NestedMap;
import com.example.libcommon.Constants.CoreCommand;
import com.example.libcommon.NewsForCategory;
import com.example.libcommon.NewsItem;
import com.example.libcommon.NewsService;

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
		core = Core.revive(this);
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
		CoreCommand command = (CoreCommand) intent.getSerializableExtra("command");
		
		Log.i(TAG,"got intent with actionName:" + actionName + " command: " + command);
		if (command != null) {

		switch (command) {
		
		case START: {
			Log.i(TAG, "start command recvd by core"); // Pretty useless command really
			break;
		}
/*		case PLAY: {

			Log.i(TAG, "PLAY Command received");
			core.play("foobar blues","blues");
			break;
		}
*/		

		case PLAYBRIEFING:
			core.playBriefing();
			break;
		
		case PAUSE: {
			core.pause();
			break;
		}
		
		case NEXT:
			core.next();
			break;
			
		case NEXTCATEGORY:
			core.nextCategory();
			
		case RESUME: {
			core.resume();
			break;
		}
		
		case GETCONFIG: {
			core.getConfig();
			break;
		}
		
		case ADDCONFIG: {
			String key = extras.getString("key");
			String val = extras.getString("val");
			core.addConfig(key,val);
			break;
		}
		
		default: 
			Log.i(TAG, "unrecognised command");
		}
		
		}

		

		return Service.START_STICKY;
	}
	
	// ----------------- U n u s e d 
	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}	
	
	// ----------------- C a l l o u t s
	
	// ----------------- GUI
	
	@Override
	public void showProgress(int progress) {
		// TODO Auto-generated method stub
		// Create suitably named intent with the right extras
		// send it
		Intent i = new Intent(Constants.displayIntent);
		i.putExtra("command", Constants.DisplayCommand.PROGRESS);
		i.putExtra("percent", progress);
		Log.v(TAG,"sending PROGRESS broadcast intent");
		sendBroadcast(i);
	}
	
	
	@Override
	public void nowPlaying(String track, String category) {

		Intent i = new Intent(Constants.displayIntent);
		i.putExtra("command", Constants.DisplayCommand.NOWPLAYING);
		i.putExtra("title", track);
		i.putExtra("category", category);
		sendBroadcast(i);
	}

	@Override
	public void setConfig(NestedMap config) {

		Intent i = new Intent(Constants.displayIntent);
		i.putExtra("command", Constants.DisplayCommand.SETCONFIG);
		i.putExtra("config", config.pack()); // !!!!! Don't forget to pack the config !!!!!
		sendBroadcast(i);
		
	}


	
	//--------------------- News Service
	
	@Override
	public NewsForCategory getNews(String category, int count) {
		return NewsService.getNews(category,count);

	}

}
