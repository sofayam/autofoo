package com.example.libcore;

import android.util.Log;

public class Player {
	
	private String TAG = "com.example.libcore.Player";
	private Boolean playing = false;
	private int progress = 0;
	
	void play (String track) {
		Log.i(TAG,"Now playing");
		playing = true;
	}
	void pause(){
		Log.i(TAG,"Paused");
	}
	void resume(){
		Log.i(TAG,"Resumed");
	}
	void stop(){
		Log.i(TAG,"Stopped");
		playing = false;
	}
	Boolean isPlaying() {
		return playing;
	}
	int getProgress() {
		return progress;
	}

}
