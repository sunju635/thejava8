package study.thejava8.interfaces.one;

public interface Foo {
    void printName();
    //void printNameUpperCase(); // 이후에 추가된 기능으로 추상 메서드를 추가하게 되면 구현한 모든 class에서 오류가 발생한다. 따라서 아래 기능 제공
    // 인터페이스에 메소드 선언이 아니라 구현체를 제공하는 방법
    // 이 기능이 제대로 동작한다는 보장이 없음. ex) getName에 정상적인 값이 들어오지 않을 수 있음. null과 같이
    // 따라서 문서화를 잘해야함 -> impleSpec 사용

    // 인스턴스가 사용 할 수 있는 것
    /**
     * @implSpec  이 구현체는 getName()으로 가져온 문자열을 대문자로 바꿔 출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    // 해당 인터페이스를 구현한 모든 인스턴스 또는 해당 타입에 관련되어 있는 유틸리티 경우 static, helper 메서드를 제공하고 싶은 경우 static 메서드 제공 가능
    static void printAnything() { //Foo 타입 가지고 참조해서 사용 가능함
        System.out.println("Foo");
    }

    String getName();
}
