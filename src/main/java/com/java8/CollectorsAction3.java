package com.java8;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.java8.CollectorsAction.menu;

public class CollectorsAction3 {

    public static void main(String[] args) {
        testPartitioningByWithPredicate();
        testPartitioningByWithPredicateAndCollectors();
        testReducingBinaryOperatir();
        testReducingBinaryOperatorAndIdentity();
        testReducingBinaryOperatorAndIdentityAndFunction();
        testSummarizingInt();
        testSummarizingDouble();
        testSummarizingLong();
    }
    private static void testPartitioningByWithPredicate(){
        System.out.println("testPartitioningByWithPredicate");
        Optional.ofNullable(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian))).ifPresent(System.out::println);

    }

    private static void testPartitioningByWithPredicateAndCollectors(){
        System.out.println("testPartitioningByWithPredicate");
        Optional.ofNullable(menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian,Collectors.averagingInt(Dish::getCalories)))).ifPresent(System.out::println);
    }
    private static void testReducingBinaryOperatir(){
        System.out.println("testReducingBinaryOperatir");
        menu.stream().
                collect(
                        Collectors.reducing(
                                BinaryOperator.maxBy(Comparator.comparingInt(Dish::getCalories)
                                )
                        )
                ).ifPresent(System.out::println);
    }
    private static void testReducingBinaryOperatorAndIdentity(){
        System.out.println("testReducingBinaryOperatorAndIdentity");
        Optional.ofNullable(menu.stream()
                .map(
                        Dish::getCalories
                )
                .collect(
                        Collectors.reducing(0,(d1,d2)->d1+d2)
                )
        ).ifPresent(System.out::println);
    }

    private static void testReducingBinaryOperatorAndIdentityAndFunction(){
        System.out.println("testReducingBinaryOperatorAndIdentityAndFunction");
        Optional.ofNullable(menu.stream()
               .collect(
                        Collectors.reducing(0,Dish::getCalories,(d1,d2)->d1+d2)
                )
        ).ifPresent(System.out::println);
    }
    private static void testSummarizingDouble(){
        System.out.println("testSummarizingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories))).ifPresent(System.out::println);
    }
    private static void testSummarizingLong(){
        System.out.println("testSummarizingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories))).ifPresent(System.out::println);
    }
    private static void testSummarizingInt(){
        System.out.println("testSummarizingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories))).ifPresent(System.out::println);
    }

}
