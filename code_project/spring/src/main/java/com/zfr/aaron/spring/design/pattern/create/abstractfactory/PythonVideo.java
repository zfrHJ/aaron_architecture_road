package com.zfr.aaron.spring.design.pattern.create.abstractfactory;

/**
 * @author 繁荣Aaron
 */
public class PythonVideo extends Video {
    @Override
    public void produce() {
        System.out.println("录制Python课程视频");
    }
}
