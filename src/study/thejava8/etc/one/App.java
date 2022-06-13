package study.thejava8.etc.one;

import java.util.Arrays;
import java.util.List;

@Chicken("양념")
@Chicken("마늘간장")
public class App {
    public static void main(String[] args){
        Chicken[] chickens = App.class.getAnnotationsByType(Chicken.class);
        Arrays.stream(chickens).forEach(c -> {
            System.out.println(c.value());
        });

        ChickenContainer chickenContainer = App.class.getAnnotation(ChickenContainer.class);
        Arrays.stream(chickenContainer.value()).forEach(c -> {
            System.out.println(c.value());
        });
    }
//    public static void main(@Chicken String[] args) throws @Chicken RuntimeException{
//        List<String> names = Arrays.asList("sunju");
//    }
//
//    //특정 타입을 사용하는 제네릭 클래스
//    //Type parameter -> @Chicken
//    static class FeelsLikeChicken<@Chicken T> {
//        //타입 파라미터, 타입
//        public static <@Chicken C> void print(@Chicken C c) {
//            System.out.println();
//        }
//    }
}
