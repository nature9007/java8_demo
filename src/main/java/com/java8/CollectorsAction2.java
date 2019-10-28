package com.java8;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.java8.CollectorsAction.menu;
public class CollectorsAction2
{
    public static void main(String[] args) {
        testGroupingByConcurrentWithFunction();
        testGroupingByConcurrentWithFunctionAndCollector();
        testGroupingByConcurrentWithFunctionAndSupplierAndCollector();
        testJoining();
        testJoiningWithDelimiter();
        testJoiningWithDelimiterAndPrefixAndSuffix();
        testMapping();
        testMaxBy();
        testMinBy();
    }
    //CollectorsAction.有final 修饰menu 为Java被动加载类，不会初始化CollectorsAction类，不占用内存
    // CollectorsAction.无final修饰menu为java主动加载类，会初始化CollectorsAction类，占用内存
    private static void testGroupingByConcurrentWithFunction(){
        System.out.println("testGroupingByConcurrentWithFunction");
        ConcurrentMap<Dish.Type, List<Dish>> concurrentMap = menu.stream().collect(Collectors.groupingByConcurrent(Dish::getType));
        Optional.ofNullable(concurrentMap.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(concurrentMap).ifPresent(System.out::println);
    }
    private static void testGroupingByConcurrentWithFunctionAndCollector(){
        System.out.println("testGroupingByConcurrentWithFunctionAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = menu.stream().
                collect(Collectors.groupingByConcurrent(Dish::getType, Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
    private static void testGroupingByConcurrentWithFunctionAndSupplierAndCollector(){
        System.out.println("testGroupingByConcurrentWithFunctionAndSupplierAndCollector");
        ConcurrentMap<Dish.Type, Double> collect = menu.stream().
                collect(Collectors.groupingByConcurrent(Dish::getType, ConcurrentSkipListMap::new ,Collectors.averagingInt(Dish::getCalories)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    private static void testJoining(){
        System.out.println("testJoining");
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining())).ifPresent(System.out::println);
    }
    private static void testJoiningWithDelimiter(){
        System.out.println("testJoiningWithDelimiter");
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(","))).ifPresent(System.out::println);
    }
    private static void testJoiningWithDelimiterAndPrefixAndSuffix(){
        System.out.println("testJoiningWithDelimiterAndPrefixAndSuffix");
        Optional.ofNullable(menu.stream().map(Dish::getName).collect(Collectors.joining(",","names[","]"))).ifPresent(System.out::println);
    }
    private static void testMapping(){
        System.out.println("testMapping");
        Optional.of(menu.stream().collect(Collectors.mapping(Dish::getName,Collectors.joining(",")))).ifPresent(System.out::println);
    }

    private static void testMaxBy(){
        System.out.println("testMaxBy");
        Optional.of(menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)))).ifPresent(System.out::println);
    }
    private static void testMinBy(){
        System.out.println("testMinBy");
        Optional.of(menu.stream().collect(Collectors.minBy(Comparator.comparingInt(Dish::getCalories)))).ifPresent(System.out::println);
    }
}
