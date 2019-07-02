package com.zfr.aaron.spring.design.pattern.structural.facade;

/**
 *@author 繁荣Aaron
 */
public class PointsPaymentService {
    public boolean pay(PointsGift pointsGift){
        //扣减积分
        System.out.println("支付"+pointsGift.getName()+" 积分成功");
        return true;
    }

}
