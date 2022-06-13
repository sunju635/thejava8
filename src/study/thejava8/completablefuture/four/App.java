package study.thejava8.completablefuture.four;

import java.util.Locale;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Future로 하기 어렵던 작업들
        //1) Future를 외부에서 완료시킬 수 없다. 취소하거나 get()에 타임아웃을 설정해야한다.
        //2) 블로킹 코드 (get())를 사용하지 않고서는 작업이 끝났을 때 콜백을 실행할 수 없다. -> 보통 비동기 코드에서 가능한 작업들. ex) get전에 get후에 작업들도 작성해두고, get 후에 이를 실행
        //3) 여러 Future를 조합할 수 없다. ex) Event 정보 가져온 다음 Event에 참석하는 회원 목록 가져오기
        //4) 예외 처리용 API를 제공하지 않는다.

        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("Sunju");
        System.out.println(future.get());

        CompletableFuture<String> future2 = CompletableFuture.completedFuture("Sunju2");
        System.out.println(future2.get());

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        future3.join(); //future3는 정의만 했기때문에 get(join)해야 실행됨.
        //join을 하면 안에서 uncatchedException으로 던져주기때문에 굳이 에러처리를 안해줘도 되서 편함.


        //결과가 왔을 때 ascyn와 같이 callback을 실행할 수 있게 가능하다. -> 여전히 get은 호출 필요
        CompletableFuture<String> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello4 " + Thread.currentThread().getName());
            return "Hello4";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(future4.get());

        //리턴이 없는 callback의 경우, 받아서 쓰기만 하는 경우
        CompletableFuture<Void> future5 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello5 " + Thread.currentThread().getName());
            return "Hello5";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        future5.get();

        //뭔가 하기만 하면 된다.
        CompletableFuture<Void> future6 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello6 " + Thread.currentThread().getName());
            return "Hello6";
        }).thenRun(() -> {//runnable
            System.out.println(Thread.currentThread().getName());
        });
        future6.get();
        //어떻게 thread pool을 만들지 않고 별도의 thread로 비동기적으로 동작했나?
        //ForkJoinPool 덕분 -> Java7
        //Excutor를 구현한 구현체로 디큐(마지막에들어온게 먼저 나감)를 써서 본인 thread가 할일이 없으면 thread가 직접 디큐에서 할일을 가져와서 작업하는 처리를 하는 framework
        //작업 단위를 자기가 파생시키는 세부적 서브 테스크가 있다면, 그 서브테스크들을 쪼개서 다른 thread에 분산시켜 처리하고, 모아서 결과값을 만들어냄

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> future7 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello7 " + Thread.currentThread().getName()); //ForkJoinPool이 아닌 pool로 나옴
            return "Hello7";
        }, executorService).thenRun(() -> {
            System.out.println("Hello7 " + Thread.currentThread().getName());
        });
        future7.get();

        //이런 callback의 pool을 나는 다른 async에서 실행하고 싶다
        CompletableFuture<Void> future8 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello8 " + Thread.currentThread().getName()); //ForkJoinPool이 아닌 pool로 나옴
            return "Hello8";
        }, executorService).thenRunAsync(() -> { //executorService을 통해 우리가 제공하는 thread pool을 사용하기 때문
            System.out.println("Hello8 " + Thread.currentThread().getName());
        }, executorService);
        future8.get();
        executorService.shutdown();
    }
}

