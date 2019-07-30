package com.zfr.aaron.redis.web;

import com.zfr.aaron.redis.lua.RateLimit;
import com.zfr.aaron.redis.zset.AnswerVoInZset;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author zfr
 * 控制器
 */
@Controller
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     *  10 秒中，可以访问5次
      */
    @RateLimit(key = "test", time = 10, count = 5)
    @GetMapping("/test")
    public String luaLimiter() {
        // 简单测试方法
        RedisAtomicInteger entityIdCounter = new RedisAtomicInteger("counter", redisTemplate.getConnectionFactory());
        String date = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS");
        return date + " 累计访问次数：" + entityIdCounter.getAndIncrement();
    }


    /**
     * 发布消息
     * @param id
     * @return
     */
    @RequestMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable String id) {


       /* redisTemplate.executePipelined(new SessionCallback<Object>() {

            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {

                //redisOperations.opsForList().leftPush()

                return null;
            }
        });*/

        redisTemplate.convertAndSend("msg1","哈哈哈，mq 订阅信息"+id);
        redisTemplate.convertAndSend("msg","哈哈哈，mq 订阅信息"+id);
        return "";
    }

    /**
     * 测试批量-redis功能
     * @return
     */
    @GetMapping("/add")
    public String addPipelined(){

        //平时一般
        Long time = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            redisTemplate.opsForValue().increment("pipline", 1);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - time));


        //pipe 管道使用
        time = System.currentTimeMillis();
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public <K, V> Object execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
                for (int i = 0; i < 10000; i++) {
                    redisTemplate.opsForValue().increment("pipline", 1L);
                }
                return null;
            }
        });
        System.out.println("耗时：" + (System.currentTimeMillis() - time));

        return  "成功";
    }





    /**
     * 布隆过滤器
     * @param id
     * @return
     */
    @RequestMapping("/lua/{id}")
    public String sendLua(@PathVariable String id) {
        //添加key值
        String script = "return redis.call('bf.add',KEYS[1],ARGV[1])";

        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>(script, Boolean.class);

        Boolean user4 = redisTemplate.execute(redisScript, Collections.singletonList("boolean:aaron:test"), String.valueOf("user"+id));

        System.out.println(user4);



        //判断是否存在
        String scriptEx = "return redis.call('bf.exists',KEYS[1],ARGV[1])";
        DefaultRedisScript<Boolean> redisScript1 = new DefaultRedisScript<>(scriptEx, Boolean.class);

        Boolean user6 = redisTemplate.execute(redisScript1, Collections.singletonList("boolean:aaron:test"), String.valueOf("user"+id));

        System.out.println(user6);

        return "";
    }

    /**
     * 排序
     * @return
     */
    @GetMapping("/zset")
    public String AddZSet(){

        String key = "mls_AnswerIdsByQuersionId:"+123;
        //添加单条
        redisTemplate.opsForZSet().add(key,234L,5);

        redisTemplate.opsForZSet().add(key,4565L,13);

        redisTemplate.opsForZSet().add(key,2345L,15);

        Set<ZSetOperations.TypedTuple<Object>> var2 = new HashSet<>();
        ZSetOperations.TypedTuple<Object> answerVoInZset1 = new AnswerVoInZset(2345L, 12);
        ZSetOperations.TypedTuple<Object> answerVoInZset2 = new AnswerVoInZset(5675L, 16);
        ZSetOperations.TypedTuple<Object> answerVoInZset4 = new AnswerVoInZset(4565L, 11);
        var2.add(answerVoInZset1);
        var2.add(answerVoInZset2);
        var2.add(answerVoInZset4);
        //批量添加
        redisTemplate.opsForZSet().add(key, var2);
        //移除单个元素
        redisTemplate.opsForZSet().remove(key,4565L);

        System.out.println();
        List<Object> recentBrowsingPositionIds = getRecentBrowsingPositionIds(1L);

        return "";

    }

    /**
     * 批量
     * @param userId
     * @return
     */
    public List<Object> getRecentBrowsingPositionIds(long userId) {
        if (userId <= 0) {
            return Collections.emptyList();
        }
        // 获取用户最近浏览的职位id
        String key = "mls_AnswerIdsByQuersionId:" + 123;
        Set<Object> positionIds = redisTemplate.opsForZSet().reverseRange(key, 0, 4);
        System.out.println(positionIds);
        return new ArrayList<Object>(positionIds);
    }



}
