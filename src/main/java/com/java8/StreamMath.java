package com.java8;

import java.util.Arrays;
import java.util.stream.Stream;

public class StreamMath {
    public static void main(String[] args) {
        Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        boolean matched = stream.allMatch(i -> i > 10);
//        assert matched : "some elements not math !";
        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        matched = stream.anyMatch(i -> i>6);
        System.out.println(matched);

        stream = Arrays.stream(new Integer[]{1,2,3,4,5,6,7,8,9,0});
        matched = stream.noneMatch(i -> i<0);
        System.out.println(matched);
    }
}
