package com.example.libcore;

public class Core {
	
	Callout callout;
	
	
	
	// ------------ S t a t e
	
	public enum CoreState {BROWSING, BRIEFING, STOPPED};
	
	CoreState coreState;
	
	// ------------ L i f e c y c l e
	
	void persist() {
		// TODO write my state out to a file
	}
	static Core revive() {
		// TODO reconstruct my state from a file
		Core core = new Core();
		core.coreState = CoreState.STOPPED;		
		return core;
	}
	
	void setCallout(Callout co) {
		callout = co;
	}
	
	void startBrowsing() {
		coreState = CoreState.BROWSING;
		
	}
	
	void startBriefing() {
		coreState = CoreState.BRIEFING;
	}
	
	

}
