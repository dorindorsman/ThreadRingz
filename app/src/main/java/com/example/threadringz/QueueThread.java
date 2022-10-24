package com.example.threadringz;

import android.util.Log;

import java.util.PriorityQueue;
import java.util.Queue;

public class QueueThread extends Thread {
    Queue<Integer> queue = new PriorityQueue<>();

    boolean isAlive = false;

    @Override
    public void run() {
        isAlive = true;

        while(isAlive) {
            if (queue.isEmpty()) {
                try {
                    Log.d("qthread", "waiting");
                    synchronized (this) {
                        wait();
                    }
                    Log.d("qthread", "notified1");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("qthread", "notified2");
            }

            Integer value = queue.remove();
            try {
                Thread.sleep(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("qthread", "task: " + value);
        }
    }

    synchronized public void addTask(Integer task) {
        queue.add(task);
        notify();
    }

    public void stopAll() {
        isAlive = false;
    }
}