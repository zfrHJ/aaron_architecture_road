package com.zfr.aaron.spring.nio;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

/**
 * 利用FileChannel对文件上共享锁限制文件只读
 */
public class FileLockDemo1 {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("F:\\development\\tmp\\hello.txt");
        FileChannel channel = in.getChannel();

        channel.lock(0, Integer.MAX_VALUE, true);
        Thread.sleep(60 * 60 * 1000);

        channel.close();
        in.close();
    }
}
