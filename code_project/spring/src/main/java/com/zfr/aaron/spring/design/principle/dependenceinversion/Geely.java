package com.zfr.aaron.spring.design.principle.dependenceinversion;

/**
 * @author 繁荣Aaron
 * 依赖倒置原则
 */
public class Geely {

    public void setiCourse(ICourse iCourse) {
        this.iCourse = iCourse;
    }

    private ICourse iCourse;



    public void studyImoocCourse(){
        iCourse.studyCourse();
    }






}
