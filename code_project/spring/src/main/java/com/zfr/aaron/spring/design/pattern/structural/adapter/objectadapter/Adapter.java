package com.zfr.aaron.spring.design.pattern.structural.adapter.objectadapter;

/**
 *@author 繁荣Aaron
 */
public class Adapter implements Target{
    private Adaptee adaptee = new Adaptee();

    @Override
    public void request() {
        //...
        adaptee.adapteeRequest();
        //...
    }
}
