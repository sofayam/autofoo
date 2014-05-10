package com.example.libcore;

import java.util.ArrayList;

import com.example.libcommon.NewsForCategory;
import com.example.libcommon.NewsItem;

public class StoryBoard {
	
	//ArrayList<NewsForCategory> playList;
	
	ArrayList<NewsItem> newsItems = new ArrayList<NewsItem>();
	int itemCtr = -1; // So that the first bumpNext gives us the first entry

	
	
	public StoryBoard(ArrayList<NewsForCategory> pl){
		//playList = pl;
		// init counters for track and category
		for (NewsForCategory nfc : pl) {
			for (NewsItem ni : nfc.items) {
				newsItems.add(ni);
			}
			
		}
	}
	
	NewsItem bumpNext() {
		itemCtr += 1;
		if (itemCtr >= newsItems.size()) {
			return null;
		}
		return newsItems.get(itemCtr);
		
		// 
		// get next track working through categories - return null if none left

	}
	
	NewsItem bumpNextCategory() {
		// get next track from next category - return null if none left
		// throw new UnsupportedOperationException();
		
		return null;
	}
	

	

}
