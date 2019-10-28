package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;

public class CustomerCollectorAction {
    public static void main(String[] args) {
        Collector<String,List<String>,List<String>> collector = new ToListCollector();

        String[] arrs =  new String[]{"Alex","wang","Hello","Lambda0","Collector","java 8"};

//        List<String> collect = Arrays.stream(arrs).filter(s -> s.length() >= 5).collect(collector);
        List<String> collect = Arrays.asList("Alex", "wang", "Hello", "Lambda0", "Collector", "java 8").parallelStream().filter(s -> s.length() >= 5).collect(collector);
        System.out.println(collect );
    }
}
