package com.zfr.aaron.spring.design.principle.singleresponsibility;

/**
 * @author 繁荣Aaron
 * 单一职责原则
 */
public interface ICourse {
    String getCourseName();
    byte[] getCourseVideo();

    void studyCourse();
    void refundCourse();

}
