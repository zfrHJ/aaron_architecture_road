package com.zfr.aaron.spring.design.behavior.observer.guavatest;

import com.google.common.eventbus.Subscribe;

/**
 *@author 繁荣Aaron
 */
public class GuavaEvent {
    @Subscribe
    public void subscribe(String str){
        //业务逻辑
        System.out.println("执行subscribe方法,传入的参数是:" + str);
    }

}

