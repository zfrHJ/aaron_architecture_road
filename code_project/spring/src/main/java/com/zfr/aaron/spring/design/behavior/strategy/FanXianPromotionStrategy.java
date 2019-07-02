package com.zfr.aaron.spring.design.behavior.strategy;

/**
 *@author 繁荣Aaron
 */
public class FanXianPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("返现促销,返回的金额存放到慕课网用户的余额中");
    }
}
