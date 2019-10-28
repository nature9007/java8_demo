package com.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {

    public static void main(String[] args) {
        Consumer<String> consumer = (s)-> System.out.println(s);
        userConsumer(consumer,"hello");

        userConsumer(System.out::println,"hello test");

        List<Apple> list = Arrays.asList(new Apple("green",120),new Apple("red",150),new Apple("abc",123));

        System.out.println(list);

        list.sort((a1,a2)-> a1.getColor().compareTo(a2.getColor()));

        System.out.println(list);

        list.stream().forEach(a-> System.out.println(a));

        System.out.println("================");

        list.stream().forEach(System.out::println);

        Function<String,Integer> function = Integer::parseInt;

        Integer result = function.apply("123");
        System.out.println(result);

        BiFunction<String, Integer, Character> stringIntegerCharacterBiFunction = String::charAt;
        Character c = stringIntegerCharacterBiFunction.apply("hello", 2);
        System.out.println(c);

        String s = new String("hello");
        Function<Integer,Character> f =  s::charAt;
        Character c2 = f.apply(4);
        System.out.println(c2);

        Supplier<String> supplier = String :: new;
        String s1 = supplier.get();
        System.out.println("==>"+s1);

        BiFunction<String,Long,Apple> appleBiFunction = Apple::new ;
        Apple apple = appleBiFunction.apply("red",100L);
        System.out.println(apple);

        ThreeFunction<String,Long,String,ComplexApple> threeFunction = ComplexApple::new;
        ComplexApple complexApple = threeFunction.apply("yello",34L,"GuoGuang");
        System.out.println(complexApple);

        List<Apple> list2 = Arrays.asList(new Apple("green",120),new Apple("red",150),new Apple("abc",123));
        list2.sort(Comparator.comparing(Apple::getColor));
        System.out.println(list2);
    }

    private static <T> void userConsumer(Consumer<T> consumer,T t){
        consumer.accept(t);
        consumer.accept(t);
    }
}
