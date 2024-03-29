package com.java8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class CompletableFutureInAction2 {

    public static void main(String[] args) throws InterruptedException {

        AtomicBoolean finished = new AtomicBoolean(false);
        ExecutorService executor1 = Executors.newFixedThreadPool(2, r->{
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });
        CompletableFuture.supplyAsync(CompletableFutureInAction1::get,executor1)
                .whenComplete((v, t)->{
                    Optional.of(v).ifPresent(System.out::println);
                    Optional.of(t).ifPresent(x->t.printStackTrace());
                    finished.set(true);
                 });
        System.out.println("==========i am no block=======");
//        while (!finished.get()){
//            Thread.sleep(10);
//        }

        // 如何在线程执行完关闭
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//        executor.execute(()-> System.out.println("test......"));
//        executor.shutdown();
        // 编程后台守护进程

        executor1.execute(()-> System.out.println("test1......"));

    }
}
