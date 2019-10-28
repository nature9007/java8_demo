package com.java8;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParalleProcessing {
    public static void main(String[] args) {
//        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("The best process time(normalAdd) =>"+ measureSumPerformance(ParalleProcessing::normalAdd,100_00_0000) +" MS");
        System.out.println("The best process time(iteratoeStream) =>"+ measureSumPerformance(ParalleProcessing::iteratoeStream,100_00_0000) +" MS");
//        System.out.println("The best process time(parallelStream) =>"+ measureSumPerformance(ParalleProcessing::parallelStream,100000000) +" MS");
//        System.out.println("The best process time(parallelStream2) =>"+ measureSumPerformance(ParalleProcessing::parallelStream2,100000000) +" MS");
        System.out.println("The best process time(parallelStream3) =>"+ measureSumPerformance(ParalleProcessing::parallelStream3,100_00_0000 ) +" MS");
    }


    private static long measureSumPerformance(Function<Long,Long> function,long limit){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i< 10; i++){
            long startTimeStamp = System.currentTimeMillis();
            long result = function.apply(limit);
            long duration = System.currentTimeMillis() - startTimeStamp;
//            System.out.println("The result of sum=>"+ result);
            if (duration<fastest)fastest =duration;
        }
        return fastest;

    }

    private static long iteratoeStream(long limit){
        return Stream.iterate(1L,i->i+1).limit(limit).reduce(0L,Long::sum);
    }
    private static long parallelStream(long limit){
        return Stream.iterate(1L,i->i+1).parallel().limit(limit).reduce(0L,Long::sum);
    }
    private static long parallelStream2(long limit){
        return Stream.iterate(1L,i->i+1).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L,Long::sum);
    }
    private static long parallelStream3(long limit){
        return LongStream.rangeClosed(1,limit).parallel().reduce(0L,Long::sum);
    }

    private static long normalAdd(long limit){
        long result = 0L;
        for (long i = 1L; i < limit; i++ ){
            result +=i;
        }
        return result;
    }
}
