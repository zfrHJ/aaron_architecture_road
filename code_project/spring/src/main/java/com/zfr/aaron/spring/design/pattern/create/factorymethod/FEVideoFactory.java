package com.zfr.aaron.spring.design.pattern.create.factorymethod;

/**
 * @author 繁荣Aaron
 */
public class FEVideoFactory extends VideoFactory{
    @Override
    public Video getVideo() {
        return new FEVideo();
    }
}
