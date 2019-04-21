package com.hitoda.demolistinjection;

import java.util.concurrent.atomic.AtomicInteger;

public interface MyInterface {
    void count();
    default void count(AtomicInteger counter) {
        System.out.printf("[%s / Your Counter @ %s ]: %d \n",
               this.getClass().getSimpleName(), Thread.currentThread() , counter.incrementAndGet());
    }
}
