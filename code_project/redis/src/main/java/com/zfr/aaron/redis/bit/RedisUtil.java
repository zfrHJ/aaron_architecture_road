package com.zfr.aaron.redis.bit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author 繁荣Aaron
 * redis工具类
 */
@Component
public class RedisUtil {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    public Boolean setBit(String key, Integer index, Boolean tag) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.setBit(key.getBytes(), index, tag));
    }

    public Boolean getBit(String key, Integer index) {
        return redisTemplate.execute((RedisCallback<Boolean>) con -> con.getBit(key.getBytes(), index));
    }

    /**
     * 统计bitmap中，value为1的个数，非常适用于统计网站的每日活跃用户数等类似的场景
     *
     * @param key
     * @return
     */
    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    public Long bitCount(String key, int start, int end) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    public Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }

    /**
     * 实现计数的加/减（ value为负数表示减）
     *
     * @param key
     * @param value
     * @return 返回redis中的值
     */
    public Long incr(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.incrBy(key.getBytes(), value));
    }

    public Long decr(String key, long value) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.decrBy(key.getBytes(), value));
    }

    /**
     * 设置 HyperLogLong 的 key 值
     * @param key
     * @param value
     */
    public Long pfadd(String key, String value){
        return redisTemplate.execute((RedisCallback<Long>) con -> con.pfAdd(key.getBytes(),value.getBytes()));
    }

    /**
     * 获取HyperLogLong 数量
     * @param key
     * @return
     */
    public Long pfCount(String key){
        return redisTemplate.execute((RedisCallback<Long>) con -> con.pfCount(key.getBytes()));
    }

    /**
     * 分布式锁
     * @param key
     * @param value
     * @return
     */
    public Boolean delLock(String key, long value) {
        // 执行lua脚本，确保原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return true else return false end";
        //String script = "redis.call('bf.add',KEYS[1],ARGV[1]) ";
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);
        return redisTemplate.execute(redisScript, Collections.singletonList(key), String.valueOf(value));
    }

}
