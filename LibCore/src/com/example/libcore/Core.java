package com.example.libcore;

public class Core {
	
	Callout callout;
	
	// ------------ S t a t e
	
	
	// ------------ L i f e c y c l e
	
	void persist() {
		// TODO write my state out to a file
	}
	static Core revive() {
		// TODO reconstruct my state from a file
		return new Core();
		
	}
	void setCallout(Callout co) {
		callout = co;
	}
	
	
	

}
