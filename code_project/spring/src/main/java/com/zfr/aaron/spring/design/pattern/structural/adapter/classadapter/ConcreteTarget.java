package com.zfr.aaron.spring.design.pattern.structural.adapter.classadapter;

/**
 *@author 繁荣Aaron
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("concreteTarget目标方法");
    }

}
