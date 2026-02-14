package org.example;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> listIntegers = new ArrayList<>(10);
        Set<String> hashSet = new HashSet<String>(); // порядок не гарантируется, а TreeSet - гарантируется
        Map<Integer, String> hashMap = new HashMap<>();
        listIntegers.add(13);
        listIntegers.add(14);
        listIntegers.add(15);
        listIntegers.add(16);
        System.out.println(listIntegers.getFirst());
        listIntegers.remove(0);

        Iterator iterator = listIntegers.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }

        for (Object object: listIntegers) {
            System.out.println(object);
        }

        for (int i = 0; i < listIntegers.size(); i++) {
            System.out.println(listIntegers.get(i));
        }

        listIntegers.clear();

        hashSet.add("Ого");

        Stream<String> stream = hashSet.stream();
        stream.forEach(System.out::println);

        hashMap.put(1,"Artem");
        

    }
}