package study.thejava8.optional.two;

import java.util.Optional;

public class OnlineClass {

    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;

    //도메인 클래스 설계의 문제로 이렇게 사용하면 안됨!
    //인스턴스 값으로 Optional을 사용하지말자.
    //public Optional<Progress> progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Optional<Progress> getProgress() {
        //null을 리턴하는것 부터 문제이므로 아래와 같이 처리함
        /*if (this.progress == null) {
            throw  new IllegalStateException();
        }*/
        
        //return null; -> 절대로 안됨. Optional을 리턴해야함.
        //정말 없다면 Optional.empty(); 리턴
        return Optional.ofNullable(progress);
    }

    /*public void setProgress(Progress progress) {
        this.progress = progress;
    }*/

    //Optional은 리턴값으로만 쓰기를 권장한다.
    public void setProgress(Optional<Progress> progress) {
        //OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        //spring_boot.setProgress(null);
        // 위 경우 둘다 null 처리에 실패
        progress.ifPresent(p-> this.progress = p);
        this.progress = progress.get();

    }

    @Override
    public String toString() {
        return "OnlineClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", closed=" + closed +
                '}';
    }
}
