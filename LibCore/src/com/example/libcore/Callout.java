package com.example.libcore;


import com.example.libcommon.NestedMap;

public interface Callout {
     // All the things the core has to tell the outside world via intents and uhu
	
	void showProgress(int progress);
	void sendConfig(NestedMap config);
	void nowPlaying(String track, String category);
	
}
