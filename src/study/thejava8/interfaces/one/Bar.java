package study.thejava8.interfaces.one;

public interface Bar extends Foo{

    void printNameUpperCase(); //인터페이스를 상속받는 인터페이스에서 다시 추상메소드로 변경 할 수 있다.
    // 이 경우 Bar를 구현하는 클래스에서 모두 구현해주어야한다.
}
