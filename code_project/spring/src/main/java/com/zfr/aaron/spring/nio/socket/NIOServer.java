package com.zfr.aaron.spring.nio.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class NIOServer {

    private static CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
    private static CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

    private static ByteBuffer readBuffer;
    private static Selector selector;
    private static LinkedBlockingQueue<SelectionKey> requestQueue;
    private static ExecutorService threadPool;

    public static void main(String[] args) {
        init();
        listen();
    }

    private static void init(){
        readBuffer = ByteBuffer.allocate(1024); // 读取请求数据的buffer缓冲
        ServerSocketChannel serverSocketChannel = null;

        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);  // NIO就是支持非阻塞的，项目实战的时候给大家来讲
            serverSocketChannel.socket().bind(new InetSocketAddress(9000), 100);
            // ServerSocket，就是负责去跟各个客户端连接连接请求的
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            // 就是仅仅关注这个ServerSocketChannel接收到的TCP连接的请求
        } catch (IOException e) {
            e.printStackTrace();
        }

        requestQueue = new LinkedBlockingQueue<SelectionKey>(500);

        threadPool = Executors.newFixedThreadPool(10);
        for(int i = 0; i < 10; i++) {
            threadPool.submit(new Worker());
        }
    }

    private static void listen() {
        while(true){
            try{
                selector.select();
                Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();

                while(keysIterator.hasNext()){
                    SelectionKey key = (SelectionKey) keysIterator.next();
                    // 可以认为一个SelectionKey是代表了一个请求
                    keysIterator.remove();
                    requestQueue.offer(key);
                }
            }
            catch(Throwable t){
                t.printStackTrace();
            }
        }
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            while(true) {
                try {
                    SelectionKey key = requestQueue.take();
                    handleRequest(key);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleRequest(SelectionKey key)
                throws IOException, ClosedChannelException {
            // 假设想象一下，后台有个线程池获取到了请求
            // 下面的代码，都是在后台线程池的工作线程里在处理和执行

            SocketChannel channel = null;

            try{
                if(key.isAcceptable()){  // 如果说这个key是个acceptable，是个连接请求的话
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    channel = serverSocketChannel.accept(); // 调用这个accept方法，就可以跟客户端进行TCP三次握手
                    // 如果三次握手成功了之后，就可以获取到一个建立好TCP连接的SocketChannel
                    // 这个SocketChannel大概可以理解为，底层有一个Socket，是跟客户端进行连接的
                    // 你的SocketChannel就是联通到那个Socket上去，负责进行网络数据的读写的
                    channel.configureBlocking(false);
                    channel.register(selector, SelectionKey.OP_READ);  // 仅仅关注这个READ请求，就是人家发送数据过来的请求
                }  // 如果说这个key是readable，是个发送了数据过来的话，此时需要读取客户端发送过来的数据
                else if(key.isReadable()){
                    channel = (SocketChannel) key.channel();
                    readBuffer.clear(); // buffer的用法，position = 0，limit = capacity，复位
                    int count = channel.read(readBuffer);  // 通过底层的socket读取数据，写入buffer中，position可能就会变成21之类的
                    // 你读取到了多少个字节，此时buffer的position就会变成多少

                    if(count > 0){
                        readBuffer.flip();   // position = 0，limit = 21，仅仅读取buffer中，0~21这段刚刚写入进去的数据
                        CharBuffer charBuffer = decoder.decode(readBuffer);
                        String request = charBuffer.toString();
                        System.out.println("服务端接收请求：" + request);
                        channel.write(encoder.encode(CharBuffer.wrap("收到".toCharArray())));
                    }
                    else{
                        channel.close();
                    }
                }
            }
            catch(Throwable t){
                t.printStackTrace();
                if(channel != null){
                    channel.close();
                }
            }
        }

    }

}
