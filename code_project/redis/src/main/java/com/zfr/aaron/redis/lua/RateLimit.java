package com.zfr.aaron.redis.lua;

import java.lang.annotation.*;

/**
 * @author zfr
 * 限流注解
 */

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    /**
     * 限流唯一标识
     * @return
     */
    String key() default "";

    /**
     * 限流时间
     */
    int time();

    /**
     * 限流次数
     * @return
     */
    int count();

}
