package com.java8;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture.supplyAsync(()->{
            System.out.println("first");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return CompletableFutureInAction1.get();
        }).whenComplete((v,t)->{
            System.out.println("second");
        }).thenApply(
                i->{
                    System.out.println("third");
                    return CompletableFutureInAction1.get();
                }
        );
        Thread.currentThread().join();
    }
}
