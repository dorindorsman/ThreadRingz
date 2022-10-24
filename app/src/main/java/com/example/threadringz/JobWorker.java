package com.example.threadringz;

import android.util.Log;

import java.util.concurrent.Semaphore;

public class JobWorker {

    Semaphore mSemaphore = new Semaphore(4);

    public void doJob(String id) throws InterruptedException {
        try {
            mSemaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d("threadjob", "starting: " + id);
        Thread.sleep(2000);
        Log.d("threadjob", "ending: " + id);

        mSemaphore.release();
    }

}
