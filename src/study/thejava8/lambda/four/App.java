package study.thejava8.lambda.four;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {
        //:: 메소드 레퍼런스. 스태틱 메소드 사용
        //UnaryOperator<String> hi = (s) -> "hi" + s;
        UnaryOperator<String> hi = Greeting::hi;

        //특정 객체의 인스턴스 메소드 사용
        Greeting greeting = new Greeting();
        // 입력값이 없는 생성자 케이스
        // 입력값은 없고, 리턴값은 객체의 타입(Greeting) -> Supplier
        UnaryOperator<String> hello = greeting::hello;
        System.out.println(hello.apply("Sunju"));

        Supplier<Greeting> newGreeting = Greeting::new;
        newGreeting.get();
        System.out.println(newGreeting.get().hello("Jung"));


        // 입력값이 있는 생성자 케이스
        Function<String, Greeting> newGreeting2 = Greeting::new;
        Greeting greeting2 = newGreeting2.apply("Sunju Jung");
        System.out.println(greeting2.getName());


        //임의 객체의 인스턴스 메소드 참조
        //String[] names = {"sunju", "gajy", "geojy"};
        String[] names = {"a", "B", "c"};
        //compareToIgnoreCase: 대소문자 무시
        //compareTo: 대문자가 앞으로
        Arrays.sort(names, String::compareTo);
        System.out.println(Arrays.toString(names));

        String test = "abc";
        //앞 글자부터 시작하여 아스크 코드 뺄샘값을 리턴한다.
        //첫 글자가 틀렸다면 뒷 글자는 영향을 받지 않고 값을 리턴한다.
        System.out.println(test.compareTo("bfd"));


    }
}
