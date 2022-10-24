package com.example.threadringz;

public class Thread2 {

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Thread 3
                Memory.isTrue = false;
            }
        }).start();
    }
}
