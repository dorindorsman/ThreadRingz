package com.example.threadringz;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final Object sync = new Object();
    private final Object syncF = new Object();

    AtomicInteger atomicValue = new AtomicInteger();

    private Integer value;

    private Float valueF;

    public void increment() {
        synchronized (sync) {
            value = value + 1;
        }
    }

    public void incrementAtomic() {
        atomicValue.incrementAndGet();
    }

    public int getValue() {
        return value;
    }

    synchronized public void incrementF() {
        synchronized (syncF) {
            valueF++;
        }
    }
}
