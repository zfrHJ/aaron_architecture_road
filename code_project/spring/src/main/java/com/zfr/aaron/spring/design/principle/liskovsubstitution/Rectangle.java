package com.zfr.aaron.spring.design.principle.liskovsubstitution;

/**
 * @author 繁荣Aaron
 * 里氏替换原则
 */
public class Rectangle implements Quadrangle {
    private long length;
    private long width;

    @Override
    public long getWidth() {
        return width;
    }

    @Override
    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
