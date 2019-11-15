package com.zfr.aaron.spring.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author aaron
 *
 *JVM堆内存溢出的时候，应该如何解决？
 * jvm设置参数：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * 错误：
 * 当前创建了第353339个对象
    java.lang.OutOfMemoryError: Java heap space
    Dumping heap to ./\java_pid42104.hprof ...
    Heap dump file created [13797095 bytes in 0.035 secs]
    Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 */
public class Demo2 {
    public static void main(String[] args) {
        long count =0;
        List<Object> list = new ArrayList<>();
        while (true){
            list.add(new Object());
            System.out.println("当前创建了第"+ (++ count) +"个对象");
        }
    }
}
