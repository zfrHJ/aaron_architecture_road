package com.zfr.aaron.spring.thread.threadPool;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ThreadPoolExample3 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("t;ask:{}"+ index);
                }
            });
        }
        executorService.shutdown();
    }
}
