package com.example.libcore;

import com.example.libcommon.NestedMap;

import android.util.Log;

public class Core {
	
	private static String TAG = "com.example.libcore.Core";
	
	Callout callout;
	
	PlayerWatcher playerWatcher;
	

	
	// ------------ S t a t e
	
	public enum CoreState {BROWSING, BRIEFING, STOPPED};
	
	CoreState coreState;
	
	NestedMap config;
	
	// ------------ L i f e c y c l e
	
	void persist() {
		// TODO write my state out to a file
	}
	static Core revive(Callout cout) {
		// TODO reconstruct my state from a file
		Core core = new Core();
		core.callout = cout;
		core.coreState = CoreState.STOPPED;		
		// TODO only ever have one playerwatcher and one player?
		core.playerWatcher = new PlayerWatcher(cout);
		core.setConfig();
		Log.i(TAG, "revived the Core");
		return core;
	}
	
	void setConfig() {
		config = new NestedMap();
		config.putNested("categories:blues:selected", "true");
		config.putNested("categories:blues:weight", "2");
		config.putNested("categories:jazz:selected", "true");
		config.putNested("categories:jazz:weight", "3");
		config.putNested("categories:folk:selected", "false");
		config.putNested("categories:folk:weight", "0");
	}
	
	void startBrowsing() {
		coreState = CoreState.BROWSING;
		
	}
	
	void startBriefing() {
		coreState = CoreState.BRIEFING;
	}
	
	void play(String track, String category) {
		playerWatcher.play(track);
		callout.nowPlaying(track,category);
	}
	
	void pause() {
		// TODO Seems to be OK just to pass these calls straight through
		// rather than just creating a bunch of boilerplate
		playerWatcher.player.pause();
	}
	
	void resume() {
		playerWatcher.player.resume();
	}
	
	void getConfig() {
		callout.setConfig(config);
	}
	public void addConfig(String key, String val) {
		config.putNested(key,val);
		Log.i(TAG,"config added: " + config);
	}
	

}
