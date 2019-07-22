package com.zfr.aaron.spring.project.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author
 */
@Configuration
public class ApplicationContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext appContext;

    /**
     * Spring自动注入applicationContext对象
     * -- 因此该Bean必须@Component被Spring scan发现
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    /**
     * 获得applicationContext
     * @return
     */
    public static ApplicationContext getAppContext() {
        return appContext;
    }

    /**
     * 根据name获取Bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return getAppContext().getBean(name);
    }

    /**
     * 根据class获取Bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getAppContext().getBean(clazz);
    }

    /**
     * 根据name、class获得Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getAppContext().getBean(name, clazz);
    }
}
