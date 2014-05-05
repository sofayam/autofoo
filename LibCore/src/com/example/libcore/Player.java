package com.example.libcore;

import android.util.Log;

public class Player {
	
	private String TAG = "com.example.libcore.Player";
	Boolean playing = false;
	int progress = 0;
	Boolean paused = false;
	Boolean finished = false;
	
	void play (String track) {
		Log.i(TAG,"Now playing");
		finished = false;
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
		if ((!paused) && playing) {
			progress = Math.min(100,progress + 25);	
		}
		if (progress >= 100) { 
			finished = true;
			playing = false; 
		};
		return progress;
	}

}
