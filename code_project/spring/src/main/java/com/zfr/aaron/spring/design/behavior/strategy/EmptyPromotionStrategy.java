package com.zfr.aaron.spring.design.behavior.strategy;

/**
 *@author 繁荣Aaron
 */
public class EmptyPromotionStrategy implements PromotionStrategy {
    @Override
    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
