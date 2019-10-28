package com.java8;

import java.util.concurrent.*;

public class FutureInAction2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(1000L);
                return "i am finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error";
            }
        });
//        String result = future.get(10, TimeUnit.MICROSECONDS);
        while (!future.isDone()){
            Thread.sleep(10);
        }
        System.out.println(future.get());
        executorService.shutdown();
    }
}
