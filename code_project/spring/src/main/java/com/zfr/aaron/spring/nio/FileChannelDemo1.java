package com.zfr.aaron.spring.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo1 {
    public static void main(String[] args) throws Exception {
        // 构造一个传统的文件输出流
        FileOutputStream out = new FileOutputStream(
                "F:\\development\\tmp\\hello.txt");
        // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
        FileChannel channel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
        channel.write(buffer);

        channel.close();
        out.close();
    }
}
