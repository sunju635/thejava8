package study.thejava8.lambda.three;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntConsumer;

public class Foo {

    public static void main(String[] args) {
        // 인자 -> 바디;
        // 바디가 한줄이면 {} 생략 가능
        Function<Integer, Integer> plus10 = (i) -> i + 10;

        //(Integer a, Integer b) -> a + b;
        // a, b) -> a + b;
        BinaryOperator<Integer> get10 = (a, b) -> a + b;

        //변수 캡쳐
        Foo foo = new Foo();
        foo.run();
    }

    private void run() {
        // 이 변수가 사실상 final 인 경우 java8부터는 final을 생략 가능하다.
        // 변수를 어디서도 변경하지 않는 경우 -> 사실상 final (effective final)
        // 이 경우(final) 로컬 클래스, 익명 클래스, 람다 모두 참조 가능
        int baseNumber = 10;

        // 로컬 클래스, 익명클래스 -> 쉐도잉이 됨 (가려짐)
        // 로컬 클래스, 익명클래스는 별도의 scope이기 때문에
        // 람다 -> 쉐도잉이 안됨.
        // 람다는 같은 scope이고, 같은 scope에서는 같은 이름의 변수를 정의 할 수 없음



        //로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); //11이 출력됨. (쉐도잉. 가려짐)
            }
        }

        //익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);  //파라미터 baseNumber이 출력됨. (쉐도잉. 가려짐)
            }
        };


        //람다
        //ctrl + 클릭
        // 조준 표시같은 select opened file 클릭하면 왼쪽에서 function interface들을 쫙 볼 수 있음
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);

        //baseNumber ++; // 람다 baseNumber가 오류가 나게됨. 더이상 effective final이 아니기 때문이다.

    }
}
