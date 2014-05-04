package com.example.libcommon;

public class Constants {
	

	// ----------- I n t e n t s  and C o m m a n d s
	
	public static String displayIntent = "com.example.libcommon.display";
	
	
	public static enum DisplayCommand {
		PROGRESS,    // int percent
		NOWPLAYING   // String title, String category 
	};
	

	
	public static String coreIntent = "com.example.libcommon.core";
	
	public static enum CoreCommand {
		START,       //
		CONFIG,      //
		PLAY,        //
		PAUSE,       //
		RESUME       //
	}; // TODO play is just for testing
	
	public static int FOO = 10;
	
	
	
}
