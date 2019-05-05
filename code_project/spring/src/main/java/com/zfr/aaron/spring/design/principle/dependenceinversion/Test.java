package com.zfr.aaron.spring.design.principle.dependenceinversion;

/**
 * @author 繁荣Aaron
 * 依赖倒置原则
 */
public class Test {

    public static void main(String[] args) {
        Geely geely = new Geely();
        geely.setiCourse(new JavaCourse());
        geely.studyImoocCourse();

        geely.setiCourse(new FECourse());
        geely.studyImoocCourse();

    }


}
