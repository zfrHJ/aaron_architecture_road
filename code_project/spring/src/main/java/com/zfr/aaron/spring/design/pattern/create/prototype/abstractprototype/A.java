package com.zfr.aaron.spring.design.pattern.create.prototype.abstractprototype;

/**
 * @author 繁荣Aaron
 */
public abstract class A implements Cloneable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
