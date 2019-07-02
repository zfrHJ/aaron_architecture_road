package com.zfr.aaron.spring.design.pattern.structural.adapter.objectadapter;

/**
 *@author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
        Target target = new ConcreteTarget();
        target.request();

        Target adapterTarget = new Adapter();
        adapterTarget.request();



    }
}
