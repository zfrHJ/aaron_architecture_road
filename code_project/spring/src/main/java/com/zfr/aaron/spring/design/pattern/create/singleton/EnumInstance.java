package com.zfr.aaron.spring.design.pattern.create.singleton;

/**
 *@author 繁荣Aaron
 */
public enum EnumInstance {
    INSTANCE{
        protected  void printTest(){
            System.out.println("繁荣Aaron Print Test");
        }
    };
    protected abstract void printTest();
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static EnumInstance getInstance(){
        return INSTANCE;
    }

}
