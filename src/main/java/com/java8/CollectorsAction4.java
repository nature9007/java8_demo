package com.java8;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.stream.Collectors;

import static com.java8.CollectorsAction.menu;

public class CollectorsAction4 {
    public static void main(String[] args) {
        testSummingDouble();
        testSummingLong();
        testSummingInt();
        testToCollection();
        testToConcurrentMap();
        testToConcurrentMapWithBinaryOperator();
        testToConcurrentMapWithBinaryOperatorAndSupplier();
        testToList();
        testToSet();
        testToMap();
        testToMapWithBinaryOperator();
        testToMapWithBinaryOperatorAndSupplier();
    }
    private static void  testSummingDouble(){
        System.out.println("testSummingDouble");
        Optional.ofNullable(menu.stream().collect(Collectors.summingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);
        Optional.of(menu.stream().map(Dish::getCalories).mapToInt(Integer::intValue).sum())
        .ifPresent(System.out::println);
    }
    private static void  testSummingLong(){
        System.out.println("testSummingLong");
        Optional.ofNullable(menu.stream().collect(Collectors.summingLong(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    private static void  testSummingInt(){
        System.out.println("testSummingInt");
        Optional.ofNullable(menu.stream().collect(Collectors.summingInt(Dish::getCalories)))
                .ifPresent(System.out::println);
    }
    private static void testToCollection(){
        System.out.println("testSummingInt");
        Optional.ofNullable(menu.stream()
                .filter(d->d.getCalories()>600)
                .collect(Collectors.toCollection(LinkedList::new))).ifPresent(System.out::println);
    }

    private static void testToConcurrentMap(){
        System.out.println("testToConcurrentMap");
        Optional.ofNullable(menu.stream()
                .collect(Collectors.toConcurrentMap(Dish::getName,Dish::getCalories))
        ).ifPresent(v->{
            System.out.println (v);
            System.out.println(v.getClass());
        });
    }

    /**
     *
     */
    private static void testToConcurrentMapWithBinaryOperator(){
        System.out.println("testToConcurrentMapWithBinaryOperator");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)->a+b))).ifPresent(System.out::println);
    }
    private static void testToConcurrentMapWithBinaryOperatorAndSupplier(){
        System.out.println("testToConcurrentMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream().collect(Collectors.toConcurrentMap(Dish::getType,v->1L,(a,b)->a+b, ConcurrentSkipListMap::new))).ifPresent(System.out::println);
    }
    private static void testToList(){
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList()))
                .ifPresent(r->{
                        System.out.println(r.getClass());
                        System.out.println(r);
                });
    }
    private static void testToSet(){
        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toSet()))
                .ifPresent(r->{
                        System.out.println(r.getClass());
                        System.out.println(r);
                });
    }
    private static void testToMap(){
        System.out.println("testToMap");

        Optional.of(menu.stream().filter(Dish::isVegetarian).collect(Collectors.toMap(Dish::getName,Dish::getCalories)))
                .ifPresent(r->{
                        System.out.println(r.getClass());
                        System.out.println(r);
                });
    }
    private static void testToMapWithBinaryOperator(){
        System.out.println("testToMapWithBinaryOperator");
        Optional.of(menu.stream()
            .collect(Collectors.toMap(Dish::getType,v->1L,(a,b)->a+b)))
                    .ifPresent( v->{
                        System.out.println(v);
                        System.out.println(v.getClass());
                    }
                );
    }
    /**
     * Type:Total
     */
    private static void testToMapWithBinaryOperatorAndSupplier() {
        System.out.println("testToMapWithBinaryOperatorAndSupplier");
        Optional.of(menu.stream()
                .collect(Collectors.toMap(Dish::getType, v -> 1L, (a, b) -> a + b, Hashtable::new)))
                .ifPresent(v -> {
                    System.out.println(v);
                    System.out.println(v.getClass());
                });
    }

}
