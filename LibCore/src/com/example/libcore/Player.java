package com.example.libcore;

import android.util.Log;

public class Player {
	
	private String TAG = "com.example.libcore.Player";
	private Boolean playing = false;
	private int progress = 0;
	private Boolean paused = false;
	
	void play (String track) {
		Log.i(TAG,"Now playing");
		playing = true;
		progress = 0;
	}
	void pause(){
		Log.i(TAG,"Paused");
		paused = true;
	}
	void resume(){
		Log.i(TAG,"Resumed");
		paused = false;
	}
	void stop(){
		Log.i(TAG,"Stopped");
		playing = false;
	}
	Boolean isPlaying() {
		return playing;
	}
	int getProgress() {
		if (!paused) {
			progress += 1;	
		}
		if (progress > 100) { 
			progress = 0; 
		};
		return progress;
	}

}
