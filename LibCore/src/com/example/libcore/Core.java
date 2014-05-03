package com.example.libcore;

import android.util.Log;

public class Core {
	
	private static String TAG = "com.example.libcore.Core";
	
	Callout callout;
	
	PlayerWatcher playerWatcher;
	
	// ------------ S t a t e
	
	public enum CoreState {BROWSING, BRIEFING, STOPPED};
	
	CoreState coreState;
	
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
		Log.i(TAG, "revived the Core");
		return core;
	}
	
	
	void startBrowsing() {
		coreState = CoreState.BROWSING;
		
	}
	
	void startBriefing() {
		coreState = CoreState.BRIEFING;
	}
	
	void play(String track) {
		playerWatcher.play(track);
	}
	
	

}
