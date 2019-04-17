package com.zfr.aaron.spring.annountaion.log.annountain;


import java.lang.annotation.*;

/**
 * @author 繁荣Aaron
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {


    /** 模块 */
    String title() default "";

    /** 功能 */
    String action() default "";

}
