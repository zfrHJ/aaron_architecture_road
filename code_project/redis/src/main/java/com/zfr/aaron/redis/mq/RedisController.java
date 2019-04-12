package com.zfr.aaron.redis.mq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;

@Controller
public class RedisController {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发布消息
     * @param id
     * @return
     */
    @RequestMapping("/sendMessage/{id}")
    public String sendMessage(@PathVariable String id) {

        redisTemplate.convertAndSend("msg1","哈哈哈，mq 订阅信息"+id);
        redisTemplate.convertAndSend("msg","哈哈哈，mq 订阅信息"+id);
        return "";
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
