package com.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class StreamInAction {
    public static void main(String[] args) {
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        Trader raoul = new Trader("Raoul","Cambridge");

        List<Transaction> transactionList = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 700),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        // 1. find all transaction in the year 2011 and sort them by value (small to high)
        List<Transaction> result = transactionList.stream().filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(toList());
        System.out.println(result);

        // 2.what are all the unique cities where the traders work;
        transactionList.stream().map(t->t.getTrader().getCity())
                .distinct().forEach(System.out::println);
        System.out.println("===============");
        //3.find all traders from Cambridge and sort them by name;
        transactionList.stream().map(Transaction::getTrader)
                .filter(t->t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);
        // 4.Return aString of all traders' name sorted alphabetically
        String reduce = transactionList.stream().map(t -> t.getTrader().getName())
                .distinct().sorted().reduce("", (name, name2) -> name + name2);
        System.out.println(reduce);

        //5.Are any traders based inMilan
        boolean liveInMilan = transactionList.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
//        transactionList.stream().map(Transaction::getTrader).anyMatch(t->t.getCity().equals("Milan"));
        System.out.println(liveInMilan);

        //  6 Print all transactions' values from the traders living in Cambridge
        transactionList.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        // 7.what's the highest value of all the transactions?
        Optional<Integer> maxValue = transactionList.stream().map(Transaction::getValue).reduce(Integer::max);
        System.out.println(maxValue.get());

        // 8.Find the transaction with the smallest value
        Optional<Integer> minValue = transactionList.stream().map(Transaction::getValue).reduce(Integer::min);
        System.out.println(minValue.get());
    }
}
