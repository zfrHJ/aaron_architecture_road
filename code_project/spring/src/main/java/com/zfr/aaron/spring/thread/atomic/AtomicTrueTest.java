package com.zfr.aaron.spring.thread.atomic;

import com.zfr.aaron.spring.thread.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zfr
 */
@ThreadSafe //线程安全
public class AtomicTrueTest {

    /**
     * 这里模拟一个递增的任务，开启10个线程每个线程减去10，总数为300。
     * 最终结果正确是200
     */
    public static void main(String[] args) throws InterruptedException {
        MyThreadDemo myThreadDemo = new MyThreadDemo();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();
        new Thread(myThreadDemo).start();

        Thread.sleep(5000);//主线程休眠5秒，得到最后结果
        System.out.println("执行结果：count=" + myThreadDemo.getCount());
    }

    static class MyThreadDemo implements Runnable{
        private AtomicInteger count = new AtomicInteger(300);//初始值300为10个线程共享
        private void decrement(int i) {
            count.decrementAndGet();
            System.out.println(Thread.currentThread().getName()+"执行"+i+"次，剩余count:"+count.get());
        }

        public int getCount() {
            return count.get();
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);//每个子线程进来先休眠100毫秒，模拟网络环境
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            for(int i=1;i<=10;i++) {
                decrement(i);
            }
        }
    }


}
