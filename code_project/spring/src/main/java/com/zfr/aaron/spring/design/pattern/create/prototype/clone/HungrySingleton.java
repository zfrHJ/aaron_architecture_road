package com.zfr.aaron.spring.design.pattern.create.prototype.clone;

import java.io.Serializable;

/**
 * @author 繁荣Aaron
 */
public class HungrySingleton implements Serializable, Cloneable{
    private final static HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton(){}

    public static HungrySingleton getInstance(){
        return hungrySingleton;
    }

    // 该方法并非被重写的方法
    private Object readResolve(){
        return hungrySingleton;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // return super.clone();
        // 解决办法，1. 不实现 Cloneable 接口，2. 实现 Cloneable 接口，重写 clone 方法，调用 单例类的 getInstance 方法
        return getInstance();
    }


}
