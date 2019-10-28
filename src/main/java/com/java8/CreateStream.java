package com.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CreateStream {
    public static void main(String[] args) {
//        createStreanFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArrays().forEach(System.out::println);
//        Stream<String> streamFromFile = createStreamFromFile();
//        System.out.println(streamFromFile);
//        streamFromFile.forEach(System.out::println);
//          createStreamFromIterator().forEach(System.out::println);
//            createStreamFromGenerate().forEach(System.out::println);
          createObjStreamFromGenerate().forEach(System.out::println);
    }

    private static Stream<String> createStreanFromCollection(){
        List<String> list = Arrays.asList("hello", "alex", "wang", "world","stream");
        return list.stream();
    }

    private static Stream<String> createStreamFromValues(){
        return Stream.of("hello", "alex", "wang", "world","stream");
    }
    private static Stream<String> createStreamFromArrays(){
        return Arrays.stream(new String[]{"hello", "alex", "wang", "world","stream"});
    }

    private static Stream<String> createStreamFromFile(){
        Path path = Paths.get("E:\\IdeaProjects\\idea2017\\jdk8\\src\\main\\java\\com\\java8\\CreateStream.java");
        try {
              Stream<String> lines = Files.lines(path);
              return lines;
          }catch (IOException e){
           throw new RuntimeException(e);
        }
    }
    private static Stream<Integer> createStreamFromIterator(){
        Stream<Integer> stream = Stream.iterate(0, n -> 2 + n);
        return stream;
    }

    private static Stream<Double> createStreamFromGenerate(){
        return Stream.generate(Math::random);
    }

    private static Stream<Obj> createObjStreamFromGenerate(){
        return Stream.generate(new ObjSupplier()).limit(10);
    }
    static class ObjSupplier implements Supplier<Obj>{
        private int index = 0;

        private Random random = new Random(System.currentTimeMillis());

        @Override
        public Obj get() {
            index = random.nextInt(100);
            return new Obj(index,"Name->"+index);
        }
    }

    static class Obj{
        private int id;
        private String  name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
