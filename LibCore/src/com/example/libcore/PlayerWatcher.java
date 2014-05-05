package com.example.libcore;


import java.util.List;

import com.example.libcommon.NewsForCategory;
import com.example.libcommon.NewsItem;

import android.util.Log;


public class PlayerWatcher {
	
	// This class watches the player, passes intra-track control messages from the core directly through
	// and manages the feeding of tracks to the player on the basis of
	// - the playlist
	// - the state of the player (e.g. current track finished)
	// - extra-track control messages from the core
	
	Boolean nextFlag = false;
	Boolean nextCategoryFlag = false;

	static final int playerDelayMS = 1000;
	
	static String TAG = "com.example.logcore.PlayerWatcher";
	
	Callout callout;
	
	Player player = new Player();
	
	PlayerWatcher(Callout co) {
		callout = co;
	};
	
	// intra-track
	
	void pause(){
		player.pause();
	}
	void resume(){
		player.resume();
	}
	void stop(){
		player.stop();
	}
	
	// extra-track
	
	void next() {
		nextFlag = true;
	}
	void nextCategory() {
		nextCategoryFlag = true;
	}

	
	void playItem(NewsItem item) {
		player.play(item.title);
		callout.nowPlaying(item.title, item.category);
	}
	
	
	public void play(final StoryBoard storyBoard) {
		// That final means that changes I make in this thread are not visible outside
		Thread progThread = new Thread() {
			public void run () {
				

				// loop through the playlist drumming your fingers and 
				// continually checking nervously for stops and pauses.
				Boolean finished = false; 
				NewsItem nextItem;
				
				nextItem = storyBoard.bumpNext();
				playItem(nextItem);
				
				while (!finished) {
					try {
						// check if current track has finished or the next flag has been set
						// move to the next one, 

						if (player.finished | nextFlag) {
							nextFlag = false;
							nextItem = storyBoard.bumpNext();
							if (nextItem == null) {
								finished = true;
							} else {
								playItem(nextItem);
							}
						}

						// check for next category

						if (nextCategoryFlag) {
							nextCategoryFlag = false;
							nextItem = storyBoard.bumpNextCategory();
							if (nextItem == null) {
								finished = true;
							} else {
								playItem(nextItem);
							}
						}
							

						// check progress and callout to GUI
						if (player.isPlaying()) {
							callout.showProgress(player.getProgress());							
						}

						
						// check for stop

						
						// check for resume


						sleep(playerDelayMS);
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}

					
			}
		};
		progThread.start();
	}
}
