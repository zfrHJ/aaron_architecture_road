package com.zfr.aaron.spring.design.pattern.create.abstractfactory;

/**
 * @author 繁荣Aaron
 */
public class JavaVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Java课程视频");
    }
}
