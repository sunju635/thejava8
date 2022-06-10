package study.thejava8.interfaces.two;

// 미리 default로 구현해놓고 시작
// 구현하는 class들은 FooSomthing2를 implement해서 사용하면됨
// 상속이 자유롭다. 상속이 자유롭다 (상속은 하나만 허용하므로)
// 비침투성 non-invasive

//WebMvcConfigurerAdater implements WebMvcConfigurer
//deprecated 됨
//비어있는 구현체들이 있는데, 기본 메소드를 사용하므로 되므로
public interface FooSomthing2 {
    default void doIt() {
        System.out.println("FooSomthing2");
    }
}
