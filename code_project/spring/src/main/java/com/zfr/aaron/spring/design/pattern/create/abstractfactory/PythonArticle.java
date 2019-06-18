package com.zfr.aaron.spring.design.pattern.create.abstractfactory;

/**
 * @author 繁荣Aaron
 */
public class PythonArticle extends Article {
    @Override
    public void produce() {
        System.out.println("编写Python课程手记");
    }
}
