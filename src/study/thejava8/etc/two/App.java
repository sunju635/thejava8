package study.thejava8.etc.two;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        
        int size = 1500;
        int[] numbers = new int[size];
        Random random = new Random();
        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());

        long start = System.nanoTime();
        Arrays.sort(numbers); //자바는 기본적으로 Dual-Pivot Quicksort 사용. 싱글스레드이기 때문에 한계가 있음
        System.out.println("serial sorting took "  + (System.nanoTime() - start));

        IntStream.range(0, size).forEach(i -> numbers[i] = random.nextInt());
        start = System.nanoTime();
        Arrays.parallelSort(numbers); // 여러 스레드로 분산해서 처리해 조금 더 빠름
        System.out.println("parallel sorting took " + (System.nanoTime() - start));
    }
}
