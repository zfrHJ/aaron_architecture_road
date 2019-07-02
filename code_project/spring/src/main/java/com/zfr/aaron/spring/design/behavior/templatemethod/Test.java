package com.zfr.aaron.spring.design.behavior.templatemethod;


/**
 *@author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
//        System.out.println("后端设计模式start---");
//        ACourse designPatternCourse = new DesignPatternCourse();
//        designPatternCourse.makeCourse();
//        System.out.println("后端设计模式end---");


        System.out.println("前端start---");
        ACourse feCourse = new FECourse(false);
        feCourse.makeCourse();
        System.out.println("前端end---");


    }
}
