package com.zfr.aaron.spring.project;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class TestMain {
    public static void main(String[] args) throws Exception{
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("redis://192.168.0.114:6379");
        RedissonClient redisson = Redisson.create(config);

        RMap<String, Object> map = redisson.getMap("anyMap");
        map.put("foo", "bar");

        map = redisson.getMap("anyMap");
        System.out.println(map.get("foo"));
        RLock lock = redisson.getLock("anyLock");
        lock.lock();
        lock.lockAsync();
        lock.lockAsync(10, TimeUnit.SECONDS);
        Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
        lock.lock(10, TimeUnit.SECONDS);

        // Wait for 100 seconds and automatically unlock it after 10 seconds
        boolean res1 = lock.tryLock(100, 10, TimeUnit.SECONDS);
        lock.unlock();


        Lock n = new RedissonMultiLock();


        RedissonRedLock redissonRedLock = new RedissonRedLock();

        redissonRedLock.lock();

    }
}
