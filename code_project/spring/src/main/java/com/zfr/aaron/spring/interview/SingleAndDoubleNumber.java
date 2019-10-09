package com.zfr.aaron.spring.interview;

/**
 * @author zfr
 *
 *蚂蚁金服第二面，题目是这样的：
    启动两个线程, 一个输出 1,3,5,7…99, 另一个输出 2,4,6,8…100 最后 STDOUT 中按序输出 1,2,3,4,5…100
    注意点，要求点：
    用 Java 的 notify 机制实现
 *
 *
 */
public class SingleAndDoubleNumber implements Runnable{

    int i = 1;

    public static void main(String[] args) {
        SingleAndDoubleNumber t = new SingleAndDoubleNumber();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);

        t1.setName("线程1");
        t2.setName("线程2");
        t1.start();
        t2.start();

    }

    @Override
    public void run() {
        while (i <= 100) {
            synchronized (this) {
                // 先唤醒另外一个线程
                notify();
                System.out.println(Thread.currentThread().getName() + ":"+ i);
                i++;
                try {
                    // 打印完之后，释放资源，等待下次被唤醒
                    if(i < 100){
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
