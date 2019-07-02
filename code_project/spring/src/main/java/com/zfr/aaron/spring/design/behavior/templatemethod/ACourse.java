package com.zfr.aaron.spring.design.behavior.templatemethod;


/**
 *@author 繁荣Aaron
 */
public abstract class ACourse {

    /**
     * 模板方法
     */
    protected final void makeCourse(){
        this.makePPT();
        this.makeVideo();
        if(needWriteArticle()){
            this.writeArticle();
        }
        this.packageCourse();
    }

    final void makePPT(){
        System.out.println("制作PPT");
    }
    final void makeVideo(){
        System.out.println("制作视频");
    }
    final void writeArticle(){
        System.out.println("编写手记");
    }

    /**
     * 钩子方法
     * @return
     */
    protected boolean needWriteArticle(){
        return false;
    }
    abstract void packageCourse();

}
