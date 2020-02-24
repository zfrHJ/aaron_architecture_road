package com.zfr.aaron.spring.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件随机写
 */
public class FileChannelDemo2 {
    public static void main(String[] args) throws Exception {
        // 构造一个传统的文件输出流
        FileOutputStream out = new FileOutputStream(
                "F:\\development\\tmp\\hello.txt");
        // 通过文件输出流获取到对应的FileChannel，以NIO的方式来写文件
        FileChannel channel = out.getChannel();

        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());
        channel.write(buffer); // 想想背后人家用Buffer的原理
        // channel必然会从buffer的position = 0的位置开始读起，一直读到limit，limit = 字符串字节数组的长度
        // buffer的position是不是就已经变成了跟limit一样了
        System.out.println(buffer.position());
        System.out.println(channel.position()); // 当前写到了文件的哪一个位置
        // 继续往磁盘文件里写，就是从FileChannel的position = 11开始写，相当于文件末尾追加

        // 如果想再次将buffer里的数据通过channel写入磁盘文件
//		buffer.rewind(); // position = 0，重新读一遍
//		channel.write(buffer); // 在文件末尾追加写的方式，顺序写

        // hello world，在hello和world中间的那个空格的地方，再写入一条数据，比如hello hello world world
        // 把一段数据插入到磁盘文件的中间，磁盘随机写
        // 在文件的随机的位置写入数据
        // 肯定是要再次从buffer中读取数据，所以position必须复位
        buffer.rewind();

        // 其次如果你要基于FileChannel随机写，可以调整FileChannel的position
        channel.position(5);
        channel.write(buffer);

        // hello world
        // hellohello world

        channel.close();
        out.close();
    }

}
