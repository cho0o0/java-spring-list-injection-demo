package com.hitoda.demolistinjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MyService {
    @Autowired
    private List<MyInterface> implementations;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

    private AtomicInteger counter = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        System.out.println("start init");
        executorService.scheduleAtFixedRate(() -> {
            implementations.forEach(it -> {
                CompletableFuture task1 = CompletableFuture.runAsync(() -> it.count(counter), executorService);
                CompletableFuture task2 = CompletableFuture.runAsync(() -> it.count(), executorService);
                try {
                    CompletableFuture.allOf(task1, task2).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        }, 1, 2, TimeUnit.SECONDS);
    }
}
