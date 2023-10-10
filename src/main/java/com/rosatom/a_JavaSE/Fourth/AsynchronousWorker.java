package com.rosatom.a_JavaSE.Fourth;

import java.util.concurrent.*;

public class AsynchronousWorker {
    public static void main(String[] args) throws InterruptedException {
        /* Using standard library */
//        scheduleUsingScheduledExecutor();

        /* Using written logic */
        scheduleUsingWrittenLogic();
    }

    private static void scheduleUsingScheduledExecutor() throws InterruptedException {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleWithFixedDelay(() -> System.err.println("scheduled: Asynchronous hello!"),
                0, 10, TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(() -> System.err.println("scheduled: Asynchronous bye!"),
                5, 10, TimeUnit.SECONDS);

        while (true) {
            Thread.sleep(1_000);
            System.err.println("main: Working.");
        }
    }

    private static void scheduleUsingWrittenLogic() throws InterruptedException {
        while (true) {
            CountDownLatch latch = new CountDownLatch(5);

            new Thread(() -> {
                System.err.println("greeting: Asynchronous hello!");

                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.err.println("greeting: Asynchronous bye!");
            }).start();

            for (int i = 0; i < 10; i++) {
                Thread.sleep(1_000);
                System.err.println("main: Working.");
                latch.countDown();
            }
        }
    }
}

