package com.zfr.aaron.spring.jvm;

/**
 * @author Aaron
 * JVM栈内存溢出的时候，应该如何解决？
 * -XX:ThreadStackSize=1m -XX:+PrintGCDetails -Xloggc:gc.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./ -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
 * 错误信息：
 * 目前是第6126次调用
    Exception in thread "main" java.lang.StackOverflowError

    主要是递归调用，产生死循环。建议循环的时候，特别要注意。
 */


public class Demo1 {

    public static  long count =0;
    public static void main(String[] args) {
        work();
    }

    private static void work() {
        System.out.println("目前是第"+ (++count)+"次调用");
        work();
    }
}
