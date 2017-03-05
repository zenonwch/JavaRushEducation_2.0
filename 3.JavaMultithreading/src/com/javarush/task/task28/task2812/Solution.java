package com.javarush.task.task28.task2812;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* 
ShutdownNow!
*/

public class Solution {
    public static void main(final String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            final int localId = i;
            executor.submit(new Runnable() {
                public void run() {
                    doExpensiveOperation(localId);
                }
            });
        }

        final List<Runnable> awaitingExecutionTasks = executor.shutdownNow();
        for (final Runnable task : awaitingExecutionTasks)
            System.out.println(task);
    }

    private static void doExpensiveOperation(final int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId="+localId);
    }
}
