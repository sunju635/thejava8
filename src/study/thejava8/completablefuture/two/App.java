package study.thejava8.completablefuture.two;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor(); //thread를 하나만 쓰는 executor
        //excutorService는 다음 작업이 들어올때가지 계속해서 대기를 함(process가 죽지 않음)
        //따라서 명시적으로 shutdown필요
        //방법1
        executorService.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });
        executorService.shutdown(); //graceful shutdown 현재 진행중인 작업을 끝까지 마치고 끝낸다.
        //executorService.shutdownNow(); //graceful하지 않고 바로 끝낸다.
        //방법2
//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread2 " + Thread.currentThread().getName());
//            }
//        });

        //ExcutorService 안에 ThreadPool이 있고, Blocking Queue가 있다.
        // Blocking Queue에 작업이 쌓이면 순서대로 진행한다.
        ExecutorService executorService2 = Executors.newFixedThreadPool(2); //2개의 thread로 번갈아가며 작업 진행
        executorService2.submit(getRunnable("Hello"));
        executorService2.submit(getRunnable("The"));
        executorService2.submit(getRunnable("Java"));

        executorService2.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(getRunnable("Hi"), 1, 2, TimeUnit.SECONDS); //2초마다 출력


        //Callable -> runnable과 같은데 return을 받을 수 있다.
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
