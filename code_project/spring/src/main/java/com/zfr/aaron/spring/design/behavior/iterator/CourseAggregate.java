package com.zfr.aaron.spring.design.behavior.iterator;

/**
 *@author 繁荣Aaron
 */
public interface CourseAggregate {

    void addCourse(Course course);
    void removeCourse(Course course);

    CourseIterator getCourseIterator();



}
