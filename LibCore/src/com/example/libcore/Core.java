package com.example.libcore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.libcommon.NestedMap;
import com.example.libcommon.NewsForCategory;

import android.util.Log;

public class Core {
	
	private static String TAG = "com.example.libcore.Core";
	
	Callout callout;
	
	PlayerWatcher playerWatcher;
	

	
	// ------------ S t a t e
	
	public enum CoreState {BROWSING, BRIEFING, STOPPED};
	
	CoreState coreState;
	
	NestedMap config;

	List <NewsForCategory> playList;
	
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
		core.refreshNews();
		Log.i(TAG, "refreshed news");
		return core;
	}
	
	void setConfig() {
		config = new NestedMap();
		
		// redundant category info makes this easier to sort later
		// TODO find a better way to handle this
		
		config.putNested("categories:sports:selected", "true");
		config.putNested("categories:sports:weight", "2");
		config.putNested("categories:sports:order", "1");
		config.putNested("categories:sports:category", "sports");

		
		config.putNested("categories:politics:selected", "true");
		config.putNested("categories:politics:weight", "3");
		config.putNested("categories:politics:order", "2");
		config.putNested("categories:politics:category", "politics");
		
		config.putNested("categories:culture:selected", "false");
		config.putNested("categories:culture:weight", "0");
		config.putNested("categories:culture:order", "0");
		config.putNested("categories:culture:category", "culture");

		
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
	

	List<NestedMap> getSortedCategories(NestedMap categories) {
		
		List<NestedMap> sorted = new ArrayList(categories.values());
	
		Collections.sort(sorted, new Comparator<NestedMap>(){
		    public int compare(NestedMap nm1, NestedMap nm2) {
		    	String s1 = (String)nm1.get("order");
		    	Integer i1 = Integer.parseInt(s1);
		    	String s2 = (String)nm2.get("order");
		    	Integer i2 = Integer.parseInt(s2);
		    	return  i2.compareTo(i1);
		    }
		});	
		
		return sorted;
		
	}
	
	void refreshNews() {
		// loop through config getting the news you are interested in at the quantity required
		// store in storyboard
		NestedMap categories = config.getNestedMap("categories");

		// Sort the categories according to order non selected is at end
		List<NestedMap> sortedCategories = getSortedCategories(categories);
		
		// Fetch news for each category 
		
		playList = new ArrayList<NewsForCategory>();
		for (NestedMap nm: sortedCategories) {
			NewsForCategory nfc = callout.getNews(
					(String)nm.get("category"),
					Integer.parseInt((String)nm.get("weight")));
			playList.add(nfc);	
		}

		Log.i("TAG", "phew!!!");
		
	}
	

}
