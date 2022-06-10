package study.thejava8.interfaces.one;

public class DefaultFoo implements Foo, Bar2{
    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }



    @Override
    public void printName() {
        System.out.println(this.name);
    }

    // 실제 문제가 생겼을 경우 재정의한 모습
    // 그리고 Foo, Bar2 둘다 printNameUpperCase() 구현체를 제공하고 있기때문에 충돌이 발생한다.
    // 따라서 이 경우 직접 오버라이딩해서 구현해주어야하며, 오버라이딩하지 않는 경우 컴파일 에러가 발생한다.
    @Override
    public void printNameUpperCase() {
        System.out.println(this.name.toUpperCase());
    }

    @Override
    public String getName() {
        return this.name;
    }
}
