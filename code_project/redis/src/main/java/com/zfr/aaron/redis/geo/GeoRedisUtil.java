package com.zfr.aaron.redis.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 繁荣Aaron
 * 位图工具库
 */
@Component
public class GeoRedisUtil {


    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 添加
     * @param k redis key
     * @param x 经度
     * @param y 维度
     * @param member  命令经纬度之后的那个key
     * @param time 过期时间
     * @return  案例：Long addedNum = redisTemplate.opsForGeo().add(key,new Point(116.405285,39.904989),"北京");
     */
    public boolean addGeo(String k, double x, double y, String member, long time) {
        String key =  k;
        try {
            GeoOperations<Object, Object> geoOps = redisTemplate.opsForGeo();
            geoOps.add(key,new Point(x,y),member);
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return true;
    }

    /**
     * 获取维度和经度信息
     * @param key redis key
     * @param value member 值
     * @return 案例：List<Point> points = redisTemplate.opsForGeo().position(cityGeoKey,"北京","上海","深圳");
     */
    public List<Point> getPoint(String key, String... value){

        List<Point> points = redisTemplate.opsForGeo().position(key,value);
        if(null != points && points.size()>0){
            return points;
        }
        return null;
    }

    /**
     * 获取距离
     * @param key redis key
     * @param startMember 两点之间的开启点
     * @param endMember 两点之间的结束点
     * @return 案例 ：Distance distance = redisTemplate.opsForGeo().distance(cityGeoKey,"北京","上海", RedisGeoCommands.DistanceUnit.KILOMETERS);
     */
    public Distance getDistance(String key, String startMember, String endMember){
        Distance distance = redisTemplate.opsForGeo().distance(key,startMember,endMember, RedisGeoCommands.DistanceUnit.KILOMETERS);
        return distance;
    }

    /**
     * 获取 最近的经纬度信息
     * @param key redis-key
     * @param x 经度
     * @param y 维度
     * @return  案例：Circle circle = new Circle(116.405285,39.904989, Metrics.KILOMETERS.getMultiplier());
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> nearByXY(String key, double x, double y){
        Circle circle = new Circle(x,y, Metrics.KILOMETERS.getMultiplier());
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo().radius(key,circle,args);
        return results;
    }

    /**
     * 获取最近的多少距离的member
     * @param key redis -key
     * @param distancePlace 距离长度 5
     * @param member 距离的地点 北京
     */
    public GeoResults<RedisGeoCommands.GeoLocation<Object>> nearByPlace(String key, Long distancePlace, String member){
        Distance distance = new Distance(distancePlace, Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates().sortAscending().limit(5);
        GeoResults<RedisGeoCommands.GeoLocation<Object>> results = redisTemplate.opsForGeo().radius(key,member,distance,args);
        return results;
    }

}
