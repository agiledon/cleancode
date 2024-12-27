package zhangyi.refactoring.mfbook;

import org.junit.jupiter.api.Test;

public class LoopPerformanceTest {
    @Test
    public void should_execute_in_one_loop() {
        long startTime = System.nanoTime();

        // 要执行的代码
        for (int i = 0; i < 1_000_000; i++) {
            // 模拟三个职责
            int abs = Math.abs(i);
            double sqrt = Math.sqrt(i);
            String temp = String.valueOf(i);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("执行耗时（纳秒）: " + duration);
        System.out.println("执行耗时（毫秒）: " + (duration / 1000000.0));
    }

    @Test
    public void should_execute_in_thee_loops() {
        long startTime = System.nanoTime();

        // 要执行的代码
        for (int i = 0; i < 1_000_000; i++) {
            // 模拟一个职责
            int abs = Math.abs(i);
        }
        for (int i = 0; i < 1_000_000; i++) {
            // 模拟一个职责
            double sqrt = Math.sqrt(i);
        }
        for (int i = 0; i < 1_000_000; i++) {
            // 模拟一个职责
            String temp = String.valueOf(i);
        }


        long endTime = System.nanoTime();
        long duration = (endTime - startTime);

        System.out.println("执行耗时（纳秒）: " + duration);
        System.out.println("执行耗时（毫秒）: " + (duration / 1000000.0));
    }
}
