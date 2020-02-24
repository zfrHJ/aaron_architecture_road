package com.zfr.aaron.spring.nio;

public class TestThread implements Runnable{

    private volatile int i = 0;

    public static void main(String[] args) {

        TestThread testThread1 = new TestThread();
        TestThread testThread2 = new TestThread();

        testThread1.run();
        testThread2.run();


    }


    @Override
    public void run() {

        try {
            synchronized (this) {
                while(true) {
                    i++;
                    System.out.println(i);
                    this.wait();
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        this.notify();
    }
}
