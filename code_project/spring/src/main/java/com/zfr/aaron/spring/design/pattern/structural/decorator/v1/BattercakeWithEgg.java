package com.zfr.aaron.spring.design.pattern.structural.decorator.v1;

/**
 *@author 繁荣Aaron
 */
public class BattercakeWithEgg extends Battercake {
    @Override
    public String getDesc() {
        return super.getDesc()+" 加一个鸡蛋";
    }

    @Override
    public int cost() {
        return super.cost()+1;
    }
}
