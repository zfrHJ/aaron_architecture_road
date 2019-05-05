package com.zfr.aaron.spring.design.principle.liskovsubstitution.methodinput;

import java.util.Map;

/**
 * @author 繁荣Aaron
 * 里氏替换原则
 */
public class Child extends Base {
//    @Override
//    public void method(HashMap map) {
//        System.out.println("子类HashMap入参方法被执行");
//    }

    public void method(Map map) {
        System.out.println("子类HashMap入参方法被执行");
    }
}
