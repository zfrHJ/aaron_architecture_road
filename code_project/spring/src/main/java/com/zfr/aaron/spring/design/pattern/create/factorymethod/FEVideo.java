package com.zfr.aaron.spring.design.pattern.create.factorymethod;

/**
 * @author 繁荣Aaron
 */
public class FEVideo extends Video{
    @Override
    public void produce() {
        System.out.println("录制FE课程视频");
    }
}
