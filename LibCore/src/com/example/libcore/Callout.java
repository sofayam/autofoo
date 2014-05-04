package com.example.libcore;


import com.example.libcommon.NestedMap;
import com.example.libcommon.NewsForCategory;

public interface Callout {
     // All the things the core has to tell the outside world via intents and uhu
	
	void showProgress(int progress);
	void nowPlaying(String track, String category);
	void setConfig(NestedMap config);
	NewsForCategory getNews(String category, int count); 
}
