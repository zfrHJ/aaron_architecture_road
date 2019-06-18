package com.zfr.aaron.spring.design.pattern.create.abstractfactory;

/**
 * @author 繁荣Aaron
 */
public class JavaArticle extends Article {
    @Override
    public void produce() {
        System.out.println("编写Java课程手记");
    }
}
