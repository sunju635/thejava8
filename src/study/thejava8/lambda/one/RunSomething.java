package study.thejava8.lambda.one;

//FunctionalInterface: 자바 스탠다드 라이브러리에 들어있는, 자바가 제공하는 어노테이션 -> import를 하지 않아도 사용 가능
//추상메서드가 2개가 되면 컴파일 오류 발생
@FunctionalInterface
public interface RunSomething {

    // abstract void doIt(); abstract 생략가능.
    // 추상메서드가 하나만 있으면 함수형 인터페이스
    // 인터페이스의 구현체가 구현해야함
    void doIt();
    //void doItAgain(); // 추상메서드가 두개 있으면 함수형 인터페이스가 아니다.

    //public static void printName() public 생략 가능
    //인터페이스 임에도 불구하고, 인터페이스 안에다가 static, default 메서드를 정의 할 수 있음
    static void printName() {
        System.out.println("sunju");
    }

    default void printAge() {
        System.out.println("31");
    }


}
