package study.thejava8.completablefuture.one;

public class App {

    public static void main(String[] args) throws InterruptedException {
        //1. 불편한 방법
        MyThread myThread = new MyThread();
        myThread.start(); //스레드는 순서 보장X
        System.out.println("Hello");

        //2. Runnable
        /*Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread2: " + Thread.currentThread().getName());
            }
        });*/

        //람다. Runnable이 functional 인터페이스로 바꼈기 때문에 가능
        Thread thread = new Thread(() -> {
            while(true) { //무한 루프
                System.out.println("Thread2: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    throw new IllegalStateException(e);
                    //return;
                }

            }
        });
        thread.start();
        System.out.println("Hello2: " + Thread.currentThread().getName());
//        thread.sleep(3000L);
//        thread.interrupt(); //종료를 시키는게 아니라 깨워서 exception이 발생되게함. 그래서 return 되는 것.

        thread.join(); //thread를 기다리도록 함. 대기하던 중 누군가가 main thread를 interrupt하면 InterruptedException이 발생해버린다. ==> 결국 스레드를 개발자가 코드로 모두 관리하기는 힘들다.
                        // 그래서 Executors를 사용
        System.out.println(thread + "is finished.");

    }

    static class  MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
