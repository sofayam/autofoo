package com.example.libcore;


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
            // setting the behavior we want from the Thread
            @Override
            public void run() {
            	int progress = 0; 
                try {
                    // a Thread loop
                    while(progress<100) {
                        progress = player.getProgress();
                        
                        sleep(1000);
                        callout.showProgress(progress);
        				Log.i("foo", "in the prog ctr thread");
 
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
}
