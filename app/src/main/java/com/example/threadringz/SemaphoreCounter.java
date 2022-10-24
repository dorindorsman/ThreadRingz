package com.example.threadringz;

import java.util.concurrent.Semaphore;

public class SemaphoreCounter {
    Semaphore semaphore = new Semaphore(1);

    private int value;

    public void increment() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        value++;
        semaphore.release();
    }

    public int getValue() {
        return value;
    }

    synchronized public void incrementF() {
    }
}
