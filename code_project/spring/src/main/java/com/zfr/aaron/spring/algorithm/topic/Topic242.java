package com.zfr.aaron.spring.algorithm.topic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Topic242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("a","b"));;
    }
    public static boolean isAnagram(String s, String t) {

        Map map = new HashMap();

        char[] chars = s.toCharArray();

        for(char c : chars){
            if(map.containsKey(c)){
                map.put(c,(Integer)map.get(c)+1);
            }else{
                map.put(c,1);
            }
        }
        Map map1 = new HashMap();
        char[] chars1 = t.toCharArray();
        for(char c : chars1){
            if(map1.containsKey(c)){
                map1.put(c,(Integer)map1.get(c)+1);
            }else{
                map1.put(c,1);
            }
        }

        if( map.size() != map1.size()){
            return false;
        }

        Iterator<Map.Entry> it1 = map1.entrySet().iterator();
        while(it1.hasNext()){
            Map.Entry entry1 = it1.next();
            Integer integer2 = (Integer) map.get(entry1.getKey());
            if(integer2 == null||(!integer2.equals(entry1.getValue()))){
                return false;
            }

            
        }

        System.out.println("test1");

        System.out.println("test2");

        System.out.println("test3");
        return true;
    }

}
