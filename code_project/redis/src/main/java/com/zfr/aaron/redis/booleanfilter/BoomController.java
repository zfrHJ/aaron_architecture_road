package com.zfr.aaron.redis.booleanfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class BoomController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    public String get() {

        BloomTest bloomer = new BloomTest();
        List<String> users = bloomer.randomUsers(100000);
        List<String> usersTrain = users.subList(0, users.size() / 2);
        List<String> usersTest = users.subList(users.size() / 2, users.size());

        redisTemplate.delete("codehole");
        for (String user : usersTrain) {
            redisTemplate.opsForValue().append("codehole", user);
        }
        int falses = 0;
        for (String user : usersTest) {
            //boolean ret = redisTemplate.opsForValue().getAndSet("codehole",user);
                /*if (ret) {
                    falses++;
                }*/
        }
        System.out.printf("%d %d\n", falses, usersTest.size());
        //client.close();

        return null;
    }
}
