package com.java8;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolAction {
    private static int[] data = {1,2,3,4,5,6,7,8,9,10};

    public static void main(String[] args) {
        System.out.println("result="+calc());
        AccmulatorRecursiveTask task = new AccmulatorRecursiveTask(0,data.length,data);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer result = forkJoinPool.invoke(task);
        System.out.println("AccmulatorRecursiveTask result : "+result);


        AccumulatorRecursiveAction action = new AccumulatorRecursiveAction(0,data.length,data);
        forkJoinPool.invoke(action);
        System.out.println("AccumulatorRecursiveAction result:" + AccumulatorRecursiveAction.AccumulatorHelper.getResult());
    }

    private static int calc(){
        int result = 0;
        for (int i = 0; i< data.length; i++){
             result += data[i];
        }
        return result;
    }
}
