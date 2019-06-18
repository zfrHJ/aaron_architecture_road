package com.zfr.aaron.spring.design.pattern.create.abstractfactory;

/**
 * @author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {
        CourseFactory courseFactory = new JavaCourseFactory();
        Video video = courseFactory.getVideo();
        Article article = courseFactory.getArticle();
        video.produce();
        article.produce();
    }
}
