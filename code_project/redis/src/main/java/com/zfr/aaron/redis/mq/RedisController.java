package com.zfr.aaron.redis.mq;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Collections;

@Controller
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

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

}
