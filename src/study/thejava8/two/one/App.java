package study.thejava8.two.one;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {

    public static void main(String[] args) {
        Foo foo = new DefaultFoo("sunju");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();// 스태틱 메서드
    }
}
