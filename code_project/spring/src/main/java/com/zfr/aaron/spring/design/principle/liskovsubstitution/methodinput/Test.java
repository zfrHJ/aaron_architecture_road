package com.zfr.aaron.spring.design.principle.liskovsubstitution.methodinput;

import java.util.HashMap;

/**
 * @author 繁荣Aaron
 * 里氏替换原则
 */
public class Test {
    public static void main(String[] args) {
        Base child = new Child();
        HashMap hashMap = new HashMap();
        child.method(hashMap);
    }
}
