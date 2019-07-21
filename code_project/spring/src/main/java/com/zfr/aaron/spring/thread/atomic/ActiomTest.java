package com.zfr.aaron.spring.thread.atomic;

import com.zfr.aaron.spring.thread.annoations.NotThreadSafe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 繁荣Aaron
 */
@NotThreadSafe //线程不安全
public class ActiomTest {
    static Logger logger = LoggerFactory.getLogger(ActiomTest.class);

    private static int n = 0;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    n++;
                    try { Thread.currentThread().sleep(10); } catch (InterruptedException e) { }
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    n++;
                    try { Thread.currentThread().sleep(10); } catch (InterruptedException e) { }
                }
            }
        };
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        logger.info("n = {}", n);
    }
}
