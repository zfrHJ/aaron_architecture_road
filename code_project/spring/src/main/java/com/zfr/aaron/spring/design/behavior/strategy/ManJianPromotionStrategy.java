package com.zfr.aaron.spring.design.behavior.strategy;

/**
 *@author 繁荣Aaron
 */
public class ManJianPromotionStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("满减促销,满200-20元");
    }
}
