package study.thejava8.lambda.one;

public class Foo {

    public static void main(String[] args) {
        // 익명 내부 클래스 anonymous inner class
        /*RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };*/

        // (한 줄 구현 형태)람다 형태로 표현
        // 특수한 형태의 object라고 볼 수 있다.
        // 함수형 인터페이스를 1라인구로 구현한 object
        // 이런 형태가 함수처럼 보이지만 이를 변수에 할당하고, 메서드에 파라미터로 전달하거나 또는 리턴타입로 리턴할 수 있다. -> First class object로 사용 할 수 있다.
        // 함수가 함수를 매개변수로 받을 수 있고 함수를 리턴할 수도 있다. -> 고차 함수
        RunSomething runSomething11 = () -> System.out.println("Hello");
        runSomething11.doIt();

        /*RunSomething runSomething2 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
                System.out.println("Lambda");
            }
        };*/

        // (다 줄 구현 형태)람다 형태로 표현
        RunSomething runSomething12 = () -> {
            System.out.println("Hello2");
            System.out.println("Lambda");
        };
        runSomething12.doIt();

        RunSomething2 runSomething21 = number -> number + 10;

        // 같은 값을 넣었을 때 항상 11이 나와야함.
        // 이것을 보장해주지 못하거나, 그러한 여지가 있을 경우 함수형 프로그래밍이라고 볼 수 없음
        System.out.println(runSomething21.doIt(1));
        System.out.println(runSomething21.doIt(1));
        System.out.println(runSomething21.doIt(1));

        System.out.println(runSomething21.doIt(2));
        System.out.println(runSomething21.doIt(2));
        System.out.println(runSomething21.doIt(2));

        //순수 함수를 달성하기 위해서는 주의가 필요하다.
        // 함수 밖에 있는 값을 참조하거나 변경하지 않는다.
        // 오로지 함수 내부에서 쓰이는 값, 함수가 전달받은 파라미터만 가지고 사용해야한다.
        int baseNumber = 10;// 순수 함수라 볼 수 없다. 상태값을 가지고 있다. 상태값에 의존한다.
        RunSomething2 runSomething22 = new RunSomething2() {
            int baseNumber = 10;// 순수 함수라 볼 수 없다. 상태값을 가지고 있다. 상태값에 의존한다.

            @Override
            public int doIt(int number) {
                baseNumber ++; //순수 함수라 볼 수 없다. 외부에 있는 값을 변경하려고함
                return number + baseNumber;
            }
        };
    }
}
