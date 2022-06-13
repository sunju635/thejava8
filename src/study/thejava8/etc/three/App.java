package study.thejava8.etc.three;

public class App {
    public static void main(String[] args) {
        //JVM 메모리 영역 중 PermGen 메모리 영역이 사라지고 Metaspace가 생겼다.

        /*
        * PermGen
        * - permanent generation, 클래스 메타데이터를 담는 곳
        * - Heap 영역에 속함 -> 메모리 관리 // (young generation(eden), old generation) 가비지 컬렉터, 등 공부 필요
        * - 기본 값으로 제한된 크기를 가지고 있음
        * - -XX:PermSize=N, PermGen 초기사이즈 설정
        * - -XX:MaxPermSize=N, PermGen 최대사이즈 설정
        * 클래스가 많아질 수록, 공간의 데이터가 많이 쌓이게 된다.
       * */

        /*
        * Metaspace
        * - 클래스 메타데이터를 담는 곳
        * - Heap영역이 아니라 Native 메모리 영역이다
        * - 기본값으로 제한된 크기를 가지고 있지 않다. (필요한 만큼 계속 늘어난다.)
        * - 자바8부터는 PermGen관련 java옵션은 무시한다.
        * - -XX:MetaspaceSize=N, Metaspace 초기사이즈 설정
        * - -XX:MaxMetaspaceSize=N, Metaspace 최대 사이즈 설정
        * */
    }
}
