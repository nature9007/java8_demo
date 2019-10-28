package com.java8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class CollectorIntroduce {
    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(
                new Apple("green",150),
                new Apple("yellow",120),
                new Apple("green",170),
                new Apple("pink",150),
                new Apple("red",120),
                new Apple("black",170));
        List<Apple> greenList = list.stream().filter(a -> a.getColor().equals("green")).collect(Collectors.toList());
        Optional.ofNullable(greenList).ifPresent(System.out::println);
        Optional.ofNullable(groupByNormal(list)).ifPresent(System.out::println);
        System.out.println("=================");
        Optional.ofNullable(groupByFunction(list)).ifPresent(System.out::println);
        System.out.println("======================");
        Optional.ofNullable(groupByCollector(list)).ifPresent(System.out::println);

    }

    private static Map<String,List<Apple>> groupByNormal(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        for (Apple apple : apples){
            List<Apple> list = map.get(apple.getColor());
            if (null == list){
                list = new ArrayList<>();
                map.put(apple.getColor(),list);
            }
            list.add(apple);
        }
        return map;
    }

    private static Map<String,List<Apple>> groupByFunction(List<Apple> apples){
        Map<String,List<Apple>> map = new HashMap<>();
        apples.stream().forEach(a->{
            List<Apple> colorList = Optional.ofNullable(map.get(a.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                map.put(a.getColor(), list);
                return list;
            });
            colorList.add(a);
        });
        return map;
    }

    private static Map<String,List<Apple>> groupByCollector(List<Apple> apples){
//        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
        return apples.stream().collect(groupingBy(Apple::getColor));
    }
}
