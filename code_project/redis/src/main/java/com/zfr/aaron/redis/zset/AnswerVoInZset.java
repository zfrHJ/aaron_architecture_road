package com.zfr.aaron.redis.zset;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class AnswerVoInZset implements ZSetOperations.TypedTuple<Object>{

    Long id;
    double score;
    public AnswerVoInZset(Long id, double score) {
        this.id = id;
        this.score = score;
    }

    @Override
    public Long getValue() {
        return id;
    }

    @Override
    public Double getScore() {
        return score;
    }

    @Override
    public int compareTo(ZSetOperations.TypedTuple<Object> o) {
        AnswerVoInZset o1 = (AnswerVoInZset) o;
        return new Double(this.score).compareTo(o1.getScore());
    }

    /**
     * 时间排序;(参考别人的)
     */
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Long> redisTempalte;

    public void addRecentBrowsingPosition(long userId, long positionId) {
        String key = "mls_AnswerIdsByQuersionId:" + 123;
        // 获取已缓存的最近浏览的职位
        ZSetOperations<String, Long> zSetOperations = redisTempalte.opsForZSet();
        // zset内部是按分数来排序的，这里用当前时间做分数
        zSetOperations.add(key, positionId, System.currentTimeMillis());
        // 环形结构--4,-3,-2,-1,0,1,2,3,4
        zSetOperations.removeRange(key, 0, -6);
    }



}
