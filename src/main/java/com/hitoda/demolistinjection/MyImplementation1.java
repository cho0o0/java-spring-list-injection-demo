package com.hitoda.demolistinjection;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyImplementation1 implements MyInterface {
    AtomicInteger myCounter = new AtomicInteger(0);

    @Override
    public void count() {
        System.out.printf("[%s / My Counter @ %s ]: %d \n",
                this.getClass().getSimpleName(), Thread.currentThread() , myCounter.incrementAndGet());
    }
}
