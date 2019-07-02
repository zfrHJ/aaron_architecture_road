package com.zfr.aaron.spring.design.pattern.structural.proxy.staticproxy;


import com.zfr.aaron.spring.design.pattern.structural.proxy.Order;

/**
 *@author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
        Order order = new Order();
        order.setUserId(2);

        OrderServiceStaticProxy orderServiceStaticProxy = new OrderServiceStaticProxy();
        orderServiceStaticProxy.saveOrder(order);
    }
}
