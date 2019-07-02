package com.zfr.aaron.spring.design.pattern.structural.decorator.v2;

/**
 *@author 繁荣Aaron
 */
public class Battercake extends ABattercake {
    @Override
    protected String getDesc() {
        return "煎饼";
    }

    @Override
    protected int cost() {
        return 8;
    }
}
