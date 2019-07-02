package com.zfr.aaron.spring.design.pattern.structural.adapter;

/**
 *@author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter();
        dc5.outputDC5V();

    }
}
