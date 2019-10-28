package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class CompletableFutreInAction3
{
    public static void main(String[] args) {
        ExecutorService executor1 = Executors.newFixedThreadPool(2, r->{
            Thread t = new Thread(r);
            t.setDaemon(false);
            return t;
        });

//        CompletableFuture.supplyAsync(CompletableFutureInAction1::get,executor1)
//                .thenApply(CompletableFutreInAction3::multiply)
//                .whenComplete((v,t)->{
//                    Optional.of(v).ifPresent(System.out::println);
//                });

        List<Integer> produtIds = Arrays.asList(1, 2, 3, 4, 5);
        Stream<CompletableFuture<Double>> completableFutureStream = produtIds.stream().map(i -> CompletableFuture.supplyAsync(() -> queryProduction(i), executor1));
        Stream<CompletableFuture<Double>> mutiplyFutures = completableFutureStream.map(future -> future.thenApply(CompletableFutreInAction3::multiply));
        List<Double> results = mutiplyFutures.map(CompletableFuture::join).collect(toList());
        System.out.println(results);
    }
    private static  double multiply(double value){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  value * 10d;
    }

    private static double queryProduction(int i){
        return CompletableFutureInAction1.get();
    }
}
