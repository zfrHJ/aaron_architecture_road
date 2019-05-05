package com.zfr.aaron.spring.design.principle.openclose;

/**
 * @author 繁荣Aaron
 * 开闭原则
 */
public class JavaDiscountCourse extends JavaCourse {

    public JavaDiscountCourse(Integer id, String name, Double price) {
        super(id, name, price);
    }

    public Double getDiscountPrice(){
        return super.getPrice()*0.8;
    }

}
