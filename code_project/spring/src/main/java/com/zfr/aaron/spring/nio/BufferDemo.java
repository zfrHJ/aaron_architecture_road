package com.zfr.aaron.spring.nio;

import java.nio.ByteBuffer;

public class BufferDemo {
    public static void main(String[] args) throws Exception {
        byte[] data = new byte[] {55, 56, 57, 58, 59};
        ByteBuffer buffer = ByteBuffer.wrap(data);

        System.out.println(buffer.capacity());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());

        System.out.println(buffer.get()); // 把当前position所在位置的数据读取一位出来
        System.out.println(buffer.position());
        buffer.mark(); // 在position = 1的时候打的mark，标记

//		buffer.position(3);
//		buffer.limit(4);

        buffer.position(3);
        System.out.println(buffer.get());
        System.out.println(buffer.position());

        buffer.reset();
        System.out.println(buffer.position());
    }
}
