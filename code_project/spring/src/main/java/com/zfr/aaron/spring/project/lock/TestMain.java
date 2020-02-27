//package com.zfr.aaron.spring.project.lock;
//
//import org.redisson.Redisson;
//import org.redisson.RedissonMultiLock;
//import org.redisson.RedissonRedLock;
//import org.redisson.api.*;
//import org.redisson.config.Config;
//
//import java.util.Date;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.locks.Lock;
//
//public class TestMain {
//    public static void main(String[] args) throws Exception{
//        Config config = new Config();
//        config.useClusterServers()
//                .addNodeAddress("redis://192.168.0.114:6379");
//        RedissonClient redisson = Redisson.create(config);
//
//        RMap<String, Object> map = redisson.getMap("anyMap");
//        map.put("foo", "bar");
//
//        map = redisson.getMap("anyMap");
//        System.out.println(map.get("foo"));
//        RLock lock = redisson.getLock("anyLock");
//        lock.lock();
//        lock.lockAsync();
//        lock.lockAsync(10, TimeUnit.SECONDS);
//        Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
//        lock.lock(10, TimeUnit.SECONDS);
//
//        // Wait for 100 seconds and automatically unlock it after 10 seconds
//        boolean res1 = lock.tryLock(100, 10, TimeUnit.SECONDS);
//        lock.unlock();
//
//
//        Lock n = new RedissonMultiLock();
//
//
//        RedissonRedLock redissonRedLock = new RedissonRedLock();
//
//        redissonRedLock.lock();
//
//        RSemaphore semaphore = redisson.getSemaphore("semaphore");
//        semaphore.trySetPermits(3);
//        for(int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]尝试获取Semaphore锁");
//                        semaphore.acquire();
//                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]成功获取到了Semaphore锁，开始工作");
//                        Thread.sleep(3000);
//                        semaphore.release();
//                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]释放Semaphore锁");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }).start();
//        }
//        RCountDownLatch latch = redisson.getCountDownLatch("anyCountDownLatch");
//        latch.trySetCount(3);
//        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]设置了必须有3个线程执行countDown，进入等待中。。。");
//
//        for(int i = 0; i < 3; i++) {
//            new Thread(new Runnable() {
//
//                @Override
//                public void run() {
//                    try {
//                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]在做一些操作，请耐心等待。。。。。。");
//                        Thread.sleep(3000);
//                        RCountDownLatch localLatch = redisson.getCountDownLatch("anyCountDownLatch");
//                        localLatch.countDown();
//                        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]执行countDown操作");
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }).start();
//        }
//
//        latch.await();
//        System.out.println(new Date() + "：线程[" + Thread.currentThread().getName() + "]收到通知，有3个线程都执行了countDown操作，可以继续往下走");
//
//
//    }
//}
