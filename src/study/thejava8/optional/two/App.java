package study.thejava8.optional.two;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // 1. isPresent
        boolean present = optional.isPresent();
        System.out.println(present);

        // 2. GET
        OnlineClass onlineClass = optional.get();
        System.out.println(onlineClass.getTitle());

        Optional<OnlineClass> optional2 = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("jpa"))
                .findFirst();
        //OnlineClass onlineClass2 = optional2.get(); // NoSuchElementException
        //System.out.println(onlineClass2.getTitle());

        // 3. ifPresent
        // optioanl 데이터로 뭔가 해야한다라고 하면 ifPresent 내부에 로직 수행
        optional.ifPresent(oc -> System.out.println(oc.getTitle()));

        // 4. orElse
        // Optional에 값이 있으면 가져오고 없는경우 ~을 리턴해라
        // 이미 만들어져 있는 인스턴스, 상수를 참고해서 사용할 때 적합
        OnlineClass onlineClass3 = optional2.orElse(createNewClass());
        System.out.println(onlineClass3.getTitle());

        // 5. orElseGet
        // Optional에 값이 있으면 가져오고 없는경우에 ~를 하라
        // orElseGet(Supplier)
        // 동적으로 작업 혹은 추가 작업이 필요할 때 적합
        OnlineClass onlineClass4 = optional2.orElseGet(App::createNewClass);
        System.out.println(onlineClass4.getTitle());

        //6. orElseThrow
        // Optional에 값이 있으면 가져오고 없는 경우 에러를 던져라
        // 기본은 NoSuchElementException
        /*OnlineClass onlineClass5 = optional2.orElseThrow(() -> {
            return new IllegalArgumentException();
        });
        onlineClass5 = optional2.orElseThrow(IllegalStateException::new);*/

        //7. filter
        // Optional에 들어있는 값 걸러내기
        // 있다는 가정하에 사용. 없다면 아무일도 일어나지 않음
        Optional<OnlineClass> optional3 = optional.filter(oc -> !oc.isClosed());
        System.out.println(optional3.isPresent());

        // 8. map
        // Optional에 있는 값 변환환
        Optional<Integer> integer = optional.map(OnlineClass::getId);
        System.out.println(integer.get());

        //아래의 경우 Optional을 모두 풀어서 사용해주어야하는 불편함 발생
        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElse(Optional.empty());

        // 위 두 스텝을 한스텝에 가능 (flatMap)
        Optional<Progress> progress2 = optional.flatMap(OnlineClass::getProgress);





    }

    private static OnlineClass createNewClass() {
        System.out.println("Creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
