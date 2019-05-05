package com.zfr.aaron.spring.design.principle.dependenceinversion;

/**
 * @author 繁荣Aaron
 * 依赖倒置原则
 */
public class PythonCourse implements ICourse {
    @Override
    public void studyCourse() {
        System.out.println("繁荣Aaron在学习Python课程");
    }
}
