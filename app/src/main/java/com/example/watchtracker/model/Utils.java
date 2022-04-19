package com.example.watchtracker.model;

import android.os.Handler;


public class Utils {

    // Delay mechanism

    public interface DelayCallback{
        void afterDelay();
    }

    public static void delay(int milliseconds, final DelayCallback delayCallback){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                delayCallback.afterDelay();
            }
        }, milliseconds); // afterDelay will be executed after (secs*1000) milliseconds.
    }
}