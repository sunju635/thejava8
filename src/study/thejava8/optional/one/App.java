package study.thejava8.optional.one;

import java.time.Duration;
import java.util.*;

public class App {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Progress progress = spring_boot.getProgress().get();
        //null check를 할 수도 있지만, 사실 null을 리턴하는것 자체가 문제임.
        if (progress != null) { // null check
            System.out.println(progress.getStudyDuration());
        }
        Duration studyDuration = spring_boot.getProgress().get().getStudyDuration();//null이므로 오류 발생
        System.out.println(studyDuration);
        
        
        //Map key type을 Optional을 쓰는것도 매우 좋지 않음
        //Map 인터페이스가 가진 특징을 깨뜨리는것!
        //가장 중요한 특징이 key는 null일 수 없다.


        //Primitive Optional이 따로 있음
        Optional.of(10); //박싱 언박싱이 벌어져서 성능이 안좋다.
        OptionalInt.of(10); //권장 방법

        //컨테이너 타입(collections, maps, streams, arrays and optionals)을 다시 Optional로 감싸지마라
        //두번 감싸게됨
        
    }
}
