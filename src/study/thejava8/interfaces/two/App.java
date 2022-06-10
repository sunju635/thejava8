package study.thejava8.interfaces.two;

import java.util.*;

public class App {

    public static void main(String[] args) {
        List<String> name = new ArrayList<>();
        name.add("sunju");
        name.add("gajy");
        name.add("geojy");
        name.add("foo");

        // 1. forEach
        System.out.println("1. forEach");
        //Consumer가 들어옴
        //String이 차례로 들어오고, 리턴하는 value가 없음
        name.forEach(s -> {
            System.out.println(s);
        });
        //메서드 레퍼런스
        name.forEach(System.out::println);

        // 2. spliterator
        System.out.println("2. spliterator");
        Spliterator<String> spliterator = name.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("====================");
        while (spliterator1.tryAdvance(System.out::println));

        // 3. stream
        System.out.println("3. stream");
        long n = name.stream().map(String::toUpperCase)
                        .filter(s -> s.startsWith("G"))
                        .count();
        System.out.println(n);

        // 4. removeIf
        System.out.println("4. removeIf");
        name.removeIf(s -> s.startsWith("g"));
        name.forEach(System.out::println);


        // 5. Comparator
        System.out.println("5. Comparator");
        name.sort(String::compareToIgnoreCase);
        name.forEach(System.out::println);
        Comparator<String> comparator = String::compareToIgnoreCase;
        name.sort(comparator.reversed());
        name.forEach(System.out::println);


    }
}
