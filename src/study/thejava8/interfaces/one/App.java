package study.thejava8.interfaces.one;

public class App {

    public static void main(String[] args) {
        Foo foo = new DefaultFoo("sunju");
        foo.printName();
        foo.printNameUpperCase();

        Foo.printAnything();// 스태틱 메서드
    }
}
