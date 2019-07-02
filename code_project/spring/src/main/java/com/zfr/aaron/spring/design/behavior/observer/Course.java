package com.zfr.aaron.spring.design.behavior.observer;

import java.util.Observable;

/**
 *@author 繁荣Aaron
 */
public class Course extends Observable{
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void produceQuestion(Course course, Question question){
        System.out.println(question.getUserName()+"在"+course.courseName+"提交了一个问题");
        setChanged();
        notifyObservers(question);

    }

}
