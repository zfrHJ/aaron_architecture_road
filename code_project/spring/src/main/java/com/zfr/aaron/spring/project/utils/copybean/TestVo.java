package com.zfr.aaron.spring.project.utils.copybean;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zfr
 *
 */

public class TestVo {

    private static final Logger logger = LoggerFactory.getLogger(TestVo.class);

    private String name;

    private String code;


    /**
     * 克隆方法
     * @param clazz class 目标对象
     * @param <T> 克隆后的对象
     * @return
     */
    public <T> T clone(Class<T> clazz)  {

        T target = null;

        try {
            target = clazz.newInstance();
        }catch (Exception e){
            logger.error("error",e);
        }

        DoToDtoUtils.copyProperties(this,target);

        return target;
    }

    public static void main(String[] args) {

        TestVo testVo = new TestVo();

        testVo.setCode("aaron");
        testVo.setName("繁荣");


        TestDTo clone = testVo.clone(TestDTo.class);


        System.out.println(clone.getName());

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
