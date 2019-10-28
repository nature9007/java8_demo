package com.java8;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FutureInAction {
    public static void main(String[] args) throws InterruptedException {
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(10000);
                return "I am finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Error";
            }
        });
        System.out.println(future.get());
        System.out.println(future.get());
        System.out.println(future.get());
        while (!future.isDone()){
            Thread.sleep(5);
        }
        System.out.println(future.get());


//        String value = block(() -> {
//            try {
//                Thread.sleep(10000);
//                return "I am finished1";
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//                return "Error";
//            }
//        });
//        System.out.println(value);
    }

    private static <T> T block(Callable<T> callable){
        return callable.action();
    }

    private static <T>Future<T> invoke(Callable<T> callable){
        AtomicReference<T> result = new AtomicReference<>();
        AtomicBoolean finished = new AtomicBoolean(false);
        Thread t = new Thread(()->{
            T value = callable.action();
            result.set(value);
        });
        t.start();
        Future<T> future = new Future<T>() {
            @Override
            public T get() {
                return result.get();
            }

            @Override
            public boolean isDone() {
                return finished.get();
            }
        };
        return future;
    }

    private interface Future<T>{
        T get();
        boolean isDone();
    }
    private interface Callable<T>{
        T action();
    }
}
