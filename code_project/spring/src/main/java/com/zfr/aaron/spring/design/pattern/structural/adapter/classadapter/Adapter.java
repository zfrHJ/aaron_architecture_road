package com.zfr.aaron.spring.design.pattern.structural.adapter.classadapter;
/**
 *@author 繁荣Aaron
 */
public class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        //...
        super.adapteeRequest();
        //...
    }
}
