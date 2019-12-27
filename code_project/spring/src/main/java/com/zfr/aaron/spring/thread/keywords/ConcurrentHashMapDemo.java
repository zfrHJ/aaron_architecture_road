package com.zfr.aaron.spring.thread.keywords;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    private Map cache =new ConcurrentHashMap<>(15);



    int key  = 10;

    public static void main(String[]args){
        ConcurrentHashMapDemo ch =    new ConcurrentHashMapDemo();
        System.out.println(ch.fibonaacci(80));
        new HashMap();
    }

    public int fibonaacci(Integer i){
        if(i==0||i ==1) {
            return i;
        }


        /*return cache.computeIfAbsent(i, (int)(key) -> {
            System.out.println("fibonaacci : "+key);
            return fibonaacci(key - 1)+fibonaacci(key - 2);
        });*/
        return 0;

    }
}
