package com.zfr.aaron.spring.thread.threadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池<br>
 * 默认的newFixedThreadPool里的LinkedBlockingQueue是一个无边界队列，如果不断的往里加任务，最终会导致内存的不可控<br>
 * 增加了有边界的队列，使用了CallerRunsPolicy拒绝策略
 *
 */
public class FangjiaThreadPoolExecutor {
    private static ExecutorService executorService = newFixedThreadPool(50);
    private static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10000), new DefaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    }
    public static void execute(Runnable command) {
        executorService.execute(command);

    }
    public static void shutdown() {
        executorService.shutdown();
    }
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;
        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "FSH-pool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
