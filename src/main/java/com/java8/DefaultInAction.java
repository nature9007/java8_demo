package com.java8;

/**
 * 1.Class akways win
 * 2.Otherwise,sub-interfaces win
 * 3.finally,if the choice is still ambiguous,the class inheriting from mutiple
 */
public class DefaultInAction {
    public static void main(String[] args) {
        A a = () -> 10;
        System.out.println(a.size());
        System.out.println(a.isEmpty());
    }
    private interface A{
        int size();

        default boolean isEmpty(){
            return size() == 0;
        }
    }
}
