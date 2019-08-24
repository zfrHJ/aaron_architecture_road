package com.zfr.aaron.spring.jvm;

/**
 * JVM 参数
 * -XX:NewSize=10485760 -XX:MaxNewSize=10485760 -XX:InitialHeapSize=20971520 -XX:MaxHeapSize=20971520
 * -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -Xloggc:E:\\data\jvm\logs\gc.log
 *
 */
public class Demo {

    public static void main(String[] args) {
        byte[] bytes = new byte[4 * 1024 * 1024];

        bytes = null;

        byte[] bytes2= new byte[2 * 128 * 1024];
        byte[] bytes1 = new byte[2 * 1024 * 1024];

        byte[] bytes4 = new byte[2 * 1024 * 1024];

        byte[] bytes5 = new byte[128 * 1024];

        byte[] bytes6 = new byte[2 * 1024 * 1024];

        byte[] bytes7 = new byte[2 * 1024 * 1024];

        byte[] bytes8 = new byte[2 * 1024 * 1024];

        byte[] bytes9 = new byte[3 * 1024 * 1024];

       /* byte[] bytes = new byte[2 * 1024 * 1024];

        bytes = new byte[2 * 1024 * 1024];
        bytes = new byte[2 * 1024 * 1024];
        bytes = null;

        byte[] bytes1 = new byte[128 * 1024];

        byte[] bytes3 = new byte[2 * 1024 * 1024];
*/
    }
}
