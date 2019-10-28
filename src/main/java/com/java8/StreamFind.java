package com.java8;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamFind {

    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        Optional<Integer> optional = stream.filter(i -> i % 2 == 0).findAny();
//        System.out.println(optional.get());

        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        Optional<Integer> optiona2 = stream.filter(i -> i % 2 == 0).findFirst();
        optiona2.ifPresent(System.out::println);
        System.out.println(optiona2.filter(i -> i == 2).get());

        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        Optional<Integer> ooptiona3 = stream.filter(i -> i < 10).findAny();
        System.out.println(ooptiona3.orElse(-1));

        int i1 = find(new Integer[]{1, 2, 3, 4, 5, 6, 7}, -1, i -> i < 100);
        System.out.println(i1);
    }
    private static int find(Integer[] values, int defaultValue, Predicate<Integer> predicate){
        for (int i:values){
            if (predicate.test(i))
                return i;
        }
        return defaultValue;
    }

}
