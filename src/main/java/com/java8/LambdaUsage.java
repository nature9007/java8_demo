package com.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaUsage {

    private static List<Apple> filter(List<Apple> sources, Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple a:sources ) {
            if (predicate.test(a)){
                result.add(a);
            }
        }
        return result;
    }

    private static List<Apple> filterByWeight(List<Apple> sources, LongPredicate longPredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple a:sources ) {
            if (longPredicate.test(a.getWeight())){
                result.add(a);
            }
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> sources, BiPredicate<String ,Long> biPredicate){
        List<Apple> result = new ArrayList<>();
        for (Apple a:sources ) {
            if (biPredicate.test(a.getColor(),a.getWeight())){
                result.add(a);
            }
        }
        return result;
    }

    private static void simpleTestConsumer(List<Apple> source,Consumer<Apple> consumer){
        for (Apple apple : source){
            consumer.accept(apple);
        }
    }

    private static void simpleBiConsumer(String c,List<Apple> source,BiConsumer<Apple,String> consumer){
        for (Apple apple : source){
            consumer.accept(apple,c);
        }
    }

    private static String  testFunction(Apple apple,Function<Apple,String> function){
        return function.apply(apple);
    }

    private static Apple testBiFunction(String color,Long weight,BiFunction<String,Long,Apple> biFunction){
        return biFunction.apply(color,weight);
    }

    public static void main(String[] args) {
//        Runnable runnable = ()-> System.out.println("helo");
//        Runnable runnable2 = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello2");
//            }
//        };
//        process(runnable);
//        process(runnable2);
//        process(()-> System.out.println("hello3"));


        List<Apple> list = Arrays.asList(new Apple("green",120),new Apple("red",150));
        List<Apple> greenList = filter(list, (apple -> apple.getColor().equals("green")));
        System.out.println(greenList);

        List<Apple> list1 = filterByWeight(list, (w) -> w > 130);
        System.out.println(list1);

        List<Apple> result2 = filterByBiPredicate(list,(s,w)-> s.equals("green") && w> 100);
        System.out.println(result2 );

        System.out.println("==============");
        simpleTestConsumer(list,a-> System.out.println(a));

        simpleBiConsumer("XXXX",list,(a,s)-> System.out.println(s+a.getColor()+":Weight=>"+ a.getWeight()));

        String yellow = testFunction(new Apple("yellow", 100), (a) -> a.toString());
        System.out.println(yellow);

        IntFunction f = i-> i*100.0d;
        Double de = (Double) f.apply(10);
        System.out.println(de);

        Apple blueapple = testBiFunction("blue", 130L, (s, w) -> new Apple(s, w));
        System.out.println(blueapple );

        Supplier<String> supplier = String :: new; // 函数推到
        System.out.println(supplier.get().getClass() );

        System.out.println("==================");
        Apple pink = createApple(() -> new Apple("pink", 100));
        System.out.println(pink);


    }
    private static void process(Runnable r){
        r.run();
    }

    private static Apple createApple(Supplier<Apple> supplier){
        return supplier.get();
    }
}
