package com.zfr.aaron.spring.project.utils.copybean;

import net.sf.cglib.beans.BeanCopier;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zfr
 * 类之间的拷贝 - 原型模式
 */
public class DoToDtoUtils {

    /**
     * BeanCopier缓存
     */
    public static Map<String,BeanCopier> beanCopierMap = new HashMap<>();



    /**
     * 拷贝方法将source对象的属性拷贝到target对象中去
     * @param source
     * @param target
     */
    public static void copyProperties(Object source,Object target){

        String cacheKey = source.getClass().toString() + target.getClass().toString();


        BeanCopier beanCopier = null;

        if(!beanCopierMap.containsKey(cacheKey)){
            synchronized (DoToDtoUtils.class){
                if(!beanCopierMap.containsKey(cacheKey)){
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierMap.put(cacheKey,beanCopier);
                }else{
                    beanCopier = beanCopierMap.get(cacheKey);
                }
            }
        }else {
            beanCopier = beanCopierMap.get(cacheKey);
        }

        beanCopier.copy(source,target,null);

    }

    public static void main(String[] args) {
        getBoxWeight(0.072D,"EA");
        System.out.println(getBoxWeight(0.072D,"EA"));
    }

    private static String getBoxWeight(Double boxWeight,String unitCode){
        DecimalFormat df = new DecimalFormat("0.000");
        String str = df.format(boxWeight);
        int i = str.indexOf(".");
        String weight=str.substring(0,i)+str.substring(i+1,str.length());

        if (unitCode.equals("EA")){
            String s = boxWeight.toString().substring(0, 1);
            weight="000"+s;
        }

        return weight;
    }
}
