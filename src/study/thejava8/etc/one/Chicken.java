package study.thejava8.etc.one;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
//@Target(ElementType.TYPE_PARAMETER) //파라미터 타입에만 사용가능하다.
@Target(ElementType.TYPE_USE) //타입을 사용하는 모든곳에 사용가능하다.
@Repeatable(ChickenContainer.class) //여러개의 annotation을 감싸고 있을 container annotaion type을 선언해야함
//컨테이너가 가지고있는 retention이랑 target 정보는 자기 자신이 감쌀 annotaion 보다 같거나 넓어야한다.
public @interface Chicken {
    String value();
}
