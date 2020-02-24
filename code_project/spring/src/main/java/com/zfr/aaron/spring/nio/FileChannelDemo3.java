package com.zfr.aaron.spring.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo3 {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream("F:\\development\\tmp\\hello.txt");
        FileChannel channel = in.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(16);
        channel.read(buffer); // 读数据写入buffer，所以写完以后，buffer的position = 16

        buffer.flip(); // position = 0，limit = 16
        byte[] data = new byte[16];
        buffer.get(data);

        System.out.println(new String(data));

        channel.close();
        in.close();
    }
}
