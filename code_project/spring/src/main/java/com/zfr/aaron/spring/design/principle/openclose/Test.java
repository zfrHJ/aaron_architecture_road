package com.zfr.aaron.spring.design.principle.openclose;

/**
 * @author 繁荣Aaron
 * 开闭原则
 */
public class Test {
    public static void main(String[] args) {
        ICourse iCourse = new JavaDiscountCourse(1, "设计思想系列(一)", 348d);
        JavaDiscountCourse javaCourse = (JavaDiscountCourse) iCourse;
        System.out.println("课程ID:" + javaCourse.getId() + " 课程名称:" + javaCourse.getName() + " 课程原价:" + javaCourse.getPrice() + " 课程折后价格:" + javaCourse.getDiscountPrice() + "元");


    }
}
