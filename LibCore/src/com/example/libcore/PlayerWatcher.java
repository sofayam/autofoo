package com.example.libcore;


import java.util.List;

import com.example.libcommon.NewsForCategory;

import android.util.Log;


public class PlayerWatcher {

	static String TAG = "com.example.logcore.PlayerWatcher";
	
	Callout callout;
	
	Player player = new Player();
	
	PlayerWatcher(Callout co) {
		callout = co;
	};
	
	void play(String s) {
		Thread progThread = new Thread() {
 
            @Override
            public void run() {
            	int progress = 0; 
                try {
       
                    while(progress<200) {
                        progress = player.getProgress();
                    
                        sleep(1000);
                        callout.showProgress(progress);
        				Log.v(TAG, "in the progress ctr thread");
 
                    }
                } catch(InterruptedException e) {
                    // don't forget to deal with exceptions....
                } finally {
                    // this block always executes so take care here of
                    // unfinished business
                }
            }
        };
        progThread.start();
        // ...
	}

	public void playBriefing(final List<NewsForCategory> playList) {
		// TODO What does that final actually do for me?
		Thread progThread = new Thread() {
			public void run () {
				// loop through the playlist drumming your fingers and 
				// continually checking nervously for stops and pauses.
				for(NewsForCategory nfc : playList) {
					// TODO something amazing happens here
					// 
				}
					
			}
		};
		progThread.start();
	}
}
