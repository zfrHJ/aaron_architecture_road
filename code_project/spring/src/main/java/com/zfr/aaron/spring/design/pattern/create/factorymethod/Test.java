package com.zfr.aaron.spring.design.pattern.create.factorymethod;

/**
 * @author 繁荣Aaron
 */
public class Test {
    public static void main(String[] args) {


        VideoFactory videoFactory = new PythonVideoFactory();
        VideoFactory videoFactory2 = new JavaVideoFactory();
        VideoFactory videoFactory3 = new FEVideoFactory();
        Video video = videoFactory.getVideo();
        video.produce();

    }

}
