package com.example.threadringz;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueThreadPool {
    ExecutorService pool = Executors.newFixedThreadPool(5);

    synchronized public void addTask(Integer task) {
        pool.execute(new Runnable() {
            @Override
            public void run() {
                Log.d("poolthread", "start task: " + task);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("poolthread", "end task: " + task);
            }
        });
    }
}
