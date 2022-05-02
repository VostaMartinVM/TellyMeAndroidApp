package com.example.tellyme.view.utils;

import android.os.Handler;


public class DelayUtils {

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
        }, milliseconds);
    }
}