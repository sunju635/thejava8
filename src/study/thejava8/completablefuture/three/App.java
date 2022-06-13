package study.thejava8.completablefuture.three;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4); //thread를 하나만 쓰는 executor
        //excutorService는 다음 작업이 들어올때가지 계속해서 대기를 함(process가 죽지 않음)
        //따라서 명시적으로 shutdown필요
        //방법1
        executorService.submit(() -> {
            System.out.println("Thread " + Thread.currentThread().getName());
        });

        //Callable -> Runnable과 같고 return을 받을 수 있다.
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> sunju = () -> {
            Thread.sleep(1000L);
            return "Sunju";
        };

        //Java(3초)가 다 끝날때까지 기다린다.
        //ex) 현재 보유 주식을 다 가져와서 자산을 보여준다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, sunju));

        for(Future<String> f : futures) {
            System.out.println(f.get());
        }

        //ex) 서버 copy 3개중에 한개를 가져오라고할 때 다 기다릴 필요가 없다.
        String s = executorService.invokeAny(Arrays.asList(hello, java, sunju)); //가장 먼저 실행된 스레드
        System.out.println(s);
//        Future<String> helloFuture = executorService.submit(hello);
//        System.out.println(helloFuture.isDone());
//        System.out.println("Started!");
//        helloFuture.get(); // 블록킹 콜(get을 만난 순간 결과를 가져올때까지 기다린다.)
//        helloFuture.cancel(false);//cancel을 해도 done이 된다. 값을 가져오려고 하면(get) 에러가 발생
//        System.out.println(helloFuture.isDone());
//        System.out.println("End!!");


//        Callable<String> hello = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return null;
//            }
//        };

        executorService.shutdown();
    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }
}
