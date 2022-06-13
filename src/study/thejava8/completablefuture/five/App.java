package study.thejava8.completablefuture.five;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        //두 future간에 의존성이 있는 경우
//        CompletableFuture<String> future = hello.thenCompose(App::getWorld);
//        System.out.println(future.get());

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        //두 future간에  의존성이 없는 경우
//        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + " " + w);
//        System.out.println(future.get());

        CompletableFuture<String> number = CompletableFuture.supplyAsync(() -> {
            System.out.println("number " + Thread.currentThread().getName());
            return "number";
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(hello, number)
                .thenAccept(System.out::println);//task들의 결과가 모두 동일하다는 보장이 없고, 에러가 있는 경우도 있을 수 있음 -> null

        //아래와 같이 하면 아무것도 블로킹 되지 않음
        List<CompletableFuture<String>> futures = Arrays.asList(hello, number);
        CompletableFuture[] futuresArrays = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture<List<String>> future2 = CompletableFuture.allOf(futuresArrays)
                .thenApply(v -> {
                    return futures.stream()
                            .map(f -> f.join())
                            .collect(Collectors.toList());
                });

        future2.get().forEach(System.out::println);

        //아무거나 하나 빨리 끝나는 결과 받기
        CompletableFuture<Void> future3 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future3.get();


        boolean throwError = true;

        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });
        System.out.println(hello2.get());

        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if(ex != null) {
                System.out.println(ex);
                return "ERROR!!";
            }
            return result;
        });
        System.out.println(hello3.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + "World ";
        });
    }
}

