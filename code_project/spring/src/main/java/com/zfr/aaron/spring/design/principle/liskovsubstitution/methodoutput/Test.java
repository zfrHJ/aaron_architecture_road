package com.zfr.aaron.spring.design.principle.liskovsubstitution.methodoutput;

/**
 * @author 繁荣Aaron
 * 里氏替换原则
 */
public class Test {
    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(child.method());

    }
}
