package com.zfr.aaron.spring.design.pattern.create.factorymethod;

/**
 * @author 繁荣Aaron
 */
public class PythonVideoFactory extends VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
