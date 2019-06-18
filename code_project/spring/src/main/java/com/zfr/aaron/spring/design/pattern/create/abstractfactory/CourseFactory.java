package com.zfr.aaron.spring.design.pattern.create.abstractfactory;


/**
 * @author 繁荣Aaron
 */
public interface CourseFactory {
    /**
     * 录制功能
     * @return
     */
    Video getVideo();

    /**
     * 笔记功能
     * @return
     */
    Article getArticle();

}
