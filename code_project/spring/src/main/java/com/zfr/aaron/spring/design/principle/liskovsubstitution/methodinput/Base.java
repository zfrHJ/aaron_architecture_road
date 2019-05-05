package com.zfr.aaron.spring.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;

/**
 * @author 繁荣Aaron
 * 里氏替换原则
 */
public class Base {
    public void method(HashMap map){
        System.out.println("父类被执行");
    }
}
