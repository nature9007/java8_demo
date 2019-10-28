package com.java8;

import java.util.stream.IntStream;

public class NumericStream {
    public static void main(String[] args) {
        /*Stream<Integer> stream = Arrays.stream(new Integer[]{1,2,2,3,4,5,6,7,8});
        Integer result = stream.filter(i -> i > 3).reduce(0, Integer::sum);
        Optional.of(result).ifPresent(System.out::println);

        stream = Arrays.stream(new Integer[]{1,2,2,3,4,5,6,7,8});
        Stream<Integer> integerStream = stream.filter(i -> i > 3);
        Integer result2 = integerStream.reduce(0, Integer::sum);
        System.out.println(result2);

        stream = Arrays.stream(new Integer[]{1,2,2,3,4,5,6,7,8});
        IntStream intStream = stream.mapToInt(i -> i.intValue());
        int sum = intStream.filter(i -> i > 3).sum();
        System.out.println(sum);*/


        int a = 9;
        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .boxed()
                .map(b-> new int[]{a,b, (int) Math.sqrt(a*a + b*b)})
                 .forEach(r -> System.out.println("a="+r[0]+",b="+r[1]+",c="+r[2]));
        System.out.println("=============================");
        IntStream.rangeClosed(1, 1000)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b-> new int[]{a,b, (int) Math.sqrt(a*a + b*b)})
                 .forEach(r -> System.out.println("a="+r[0]+",b="+r[1]+",c="+r[2]));
    }

}
