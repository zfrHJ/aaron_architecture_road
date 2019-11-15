package com.zfr.aaron.spring.project.utils;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;

public class BeanInit implements InitializingBean {


    public BeanInit() {
        System.out.println("MyInitializingBean....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @PostConstruct  //功能上近似init-method，但加载时机不同
    public void test(){
        System.out.println("PostConstruct >>>>>>>>>>>>");
    }

    public void testInit(){
        System.out.println("ceshi init-method");
    }


}
