package com.zfr.aaron.spring.design.principle.singleresponsibility;

/**
 * @author 繁荣Aaron
 * 单一职责原则
 */
public class CourseImpl implements ICourseManager,ICourseContent {
    @Override
    public void studyCourse() {

    }

    @Override
    public void refundCourse() {

    }

    @Override
    public String getCourseName() {
        return null;
    }

    @Override
    public byte[] getCourseVideo() {
        return new byte[0];
    }
}
