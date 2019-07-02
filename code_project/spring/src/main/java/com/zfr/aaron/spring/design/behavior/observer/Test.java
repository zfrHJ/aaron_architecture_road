package com.zfr.aaron.spring.design.behavior.observer;

/**
 *@author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
        Course course = new Course("Java设计模式精讲");
        Teacher teacher1 = new Teacher("Alpha");
        Teacher teacher2 = new Teacher("Beta");


        course.addObserver(teacher1);
        course.addObserver(teacher2);

        //业务逻辑代码
        Question question = new Question();
        question.setUserName("繁荣Aaron");
        question.setQuestionContent("Java的主函数如何编写");

        course.produceQuestion(course,question);

    }
}
