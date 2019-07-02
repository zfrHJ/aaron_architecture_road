package com.zfr.aaron.spring.design.pattern.structural.proxy;

/**
 *@author 繁荣Aaron
 */
public class OrderDaoImpl implements IOrderDao {
    @Override
    public int insert(Order order) {
        System.out.println("Dao层添加Order成功");
        return 1;
    }
}
