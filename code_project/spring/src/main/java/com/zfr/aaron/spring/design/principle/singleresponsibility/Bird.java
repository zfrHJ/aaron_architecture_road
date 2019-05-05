package com.zfr.aaron.spring.design.principle.singleresponsibility;

/**
 * @author 繁荣Aaron
 * 单一职责原则
 */
public class Bird {
    public void mainMoveMode(String birdName){
        if("鸵鸟".equals(birdName)){
            System.out.println(birdName+"用脚走");
        }else{
            System.out.println(birdName+"用翅膀飞");
        }
    }
}
