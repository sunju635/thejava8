package study.thejava8.lambda.two;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        Function<Integer, Integer> plus10 = (i) -> i + 10;
        //Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(multiply2.apply(1));

        // 고차 함수(Higher-Order Function)
        // (i * 2) + 10
        Function<Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));
        // (i + 10) * 2
        System.out.println(plus10.andThen(multiply2).apply(2));

        //BiFunction: 입력값 두개 받아서 리턴
        //Consumer: 리턴 없음
        Consumer<Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);
        //Supplier: 받아올 값을 정의. 입력값X
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10.get());
        //Predicate: 인자값을 받아서 true, false 리턴
        Predicate<String> startsWithSunju = (s) -> s.startsWith("sunju");
        System.out.println(startsWithSunju.test("sunju Jung"));
        Predicate<Integer> isEven = (i) -> i%2 == 0;
        System.out.println(isEven.test(4));

        //UnaryOperator 입력값, 결과값 타입이 같은 경우
        UnaryOperator<Integer> plus5 = (i) -> i + 5;
        //Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1));

        // BinaryOperator: BiFunction의 특수한 형태
        // 세개의 타입이 같은 경우

    }
}
