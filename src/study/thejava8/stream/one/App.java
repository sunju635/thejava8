package study.thejava8.stream.one;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        /*
        * Stream
        * */

        List<String> names = new ArrayList<>();
        names.add("sunju");
        names.add("jung");

        //Functional in nature, 스트림이 처리하는데이터 소스를 변경하지 않는다.
        //데이터가 변경되는 것이 아니다.
        Stream<String> stringStream = names.stream()
                .map(String::toUpperCase);
        names.forEach(System.out::println);
        System.out.println("==============");

        //무제한일 수도 있다. (Short Circuit 메소드를 사용해서 제한할 수 있다.)
        //중개 오퍼레이션은 근본적으로 lazy하다.
        // 종료 오퍼레이션, 중개 오퍼레이션 두가지가 있다.

        /*
        * 중개 오퍼레인션
        * - Stream을 리턴한다.
        * - Stateless / Stateful 오퍼레이션으로 더 상세하기 구분 할 수도 있다.
        * (대부분은 Stateless지만 distict나 sorted처럼 이전 소스 데이터를 참조해야 하는
        * 오퍼레이션은 Stateful 오퍼레이션이다.
        * filter, map, limit, skip, sorted, ...
        *
        *
        * 종료 오퍼레이션
        * Stream을 리턴하지 않는다.
        * collect, allMatch, count, forEach, min, max, ...*/

        //중개 오퍼레이션은 여러개고, 종료형 오퍼레이션이 반드시 하나 있어야함



        stringStream = names.stream()
                .map((s) -> {
                    System.out.println(s); // 출력되지 않는다.
                    return s.toUpperCase();
                }); //Stream pipeline을 정의한것, 종료형 오퍼레이션이 실행되야만 출력된다.
        System.out.println("==============");


        List<String> collect = names.stream()
                .map((s) -> {
                    System.out.println(s); // 출력되지 않는다.
                    return s.toUpperCase();
                }).collect(Collectors.toList());

        System.out.println("==============");
        collect.forEach(System.out::println);


        //손쉽게 병렬 처리를 할 수 있다.
        System.out.println("==============");
        for(String name : names) {
            if(name.startsWith("s")){
                System.out.println(name.toUpperCase());
            }
        }

        System.out.println("==============");
        //JVM이 알아서 병렬적으로 해줌
        //spliterator 인터페이스 써서 try split해서 처리를 하게됨
        //parallelStream 무조건 좋은게 아님. 느려질 수 있음 -> 병렬 스레드, 멀티스레드가 항상 빠르지 않다.
        //스레드를 만들고, 스레드에 병렬적으로 처리하고 다 수집하고, 스레드끼리 왔다갔다하는 스위치비용...
        // stream: 하나의 스레드
        // parallelStream: 멀티 스레드
        // 데이터가 정말 방대하게 큰 경우 우용함
        names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
                }).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("==============");

        names.stream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
