package com.java8;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

public class AccumulatorRecursiveAction extends RecursiveAction {
    private final int start;
    private final int end;
    private final int[] data;
    private final int LIMIT = 3;

    public AccumulatorRecursiveAction(int start, int end, int[] data) {
        this.start = start;
        this.end = end;
        this.data = data;
    }

    @Override
    protected void compute() {
        if ((end-start) <=LIMIT){
            int result = 0;
            for (int i = start; i < end; i++){
//                result += data[i];
                AccumulatorHelper.accumelate(data[i]);
            }
        }else{
            int mid = (start + end) / 2;
            AccumulatorRecursiveAction left = new AccumulatorRecursiveAction(start,mid,data);
            AccumulatorRecursiveAction right = new AccumulatorRecursiveAction(mid,end,data);
            left.fork();
            right.fork();
            left.join();
            right.join();
        }
    }
    static class AccumulatorHelper{
        private static final AtomicInteger result = new AtomicInteger(0);
        static void accumelate(int value){
            result.getAndAdd(value);
        }
        public static int getResult(){
            return result.get();
        }
        static void reset(){
            result.set(0);
        }
    }
}
