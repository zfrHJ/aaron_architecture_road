package com.zfr.aaron.spring.nio;

import java.nio.ByteBuffer;

public class BufferDemo2 {
    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocateDirect(100);

        System.out.println("position=" + buffer.position());
        System.out.println("capacity=" + buffer.position());
        System.out.println("limit=" + buffer.limit());

        byte[] src = new byte[] {1, 2, 3, 4, 5};
        buffer.put(src);
        System.out.println("position=" + buffer.position());  // position = 0~4，都填充了数据

        // position = 5，此时如果你直接读数据，是读不到的，是空的没有数据
        // 手动操作一下position，调整到position = 0的地方，开始读数据
        buffer.position(0);
        byte[] dst = new byte[5];
        buffer.get(dst);
        System.out.println("position=" + buffer.position());

        System.out.print("dst=[");
        for(int i = 0; i < dst.length; i++) {
            System.out.print(i);
            if(i < dst.length - 1) {
                System.out.print(",");
            }
        }
        System.out.print("]");
    }

}
