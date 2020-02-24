package com.zfr.aaron.spring.nio.socket;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {

	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			new Worker().start();
		}
	}

	static class Worker extends Thread {

		@Override
		public void run() {
			SocketChannel channel = null;
			Selector selector = null;
			try {
				channel = SocketChannel.open();  // SocketChannel，你看底层一看就是封装了一个Socket
				// 他的SocketChannel是连接到底层的Socket网络连接上去的数据通道
				// 就是负责基于网络读写数据的
				channel.configureBlocking(false);
				channel.connect(new InetSocketAddress("localhost", 9000));
				// 一定是发起了一个TCP三次握手，尝试建立连接
				// 他在后台一定是跟server端在进行三次握手，如果握手成功了之后
				// 就是说一定是建立了一个连接

				selector = Selector.open();
				channel.register(selector, SelectionKey.OP_CONNECT);  // 监听connect行为

				while(true){
					selector.select();   // 服务器程序一定会给客户端返回一个响应

					Iterator<SelectionKey> keysIterator = selector.selectedKeys().iterator();
					while(keysIterator.hasNext()){
						SelectionKey key = (SelectionKey) keysIterator.next();
						keysIterator.remove();

						if(key.isConnectable()){  // 如果说server返回的是一个connectable的消息
							channel = (SocketChannel) key.channel();

							if(channel.isConnectionPending()){
								channel.finishConnect();
								// 接下来对这个SocketChannel感兴趣的就是人家server给你发送过来的数据了
								// READ事件，就是可以读数据的事件
								// 一旦建立连接成功了以后，此时就可以给server发送一个请求了
								ByteBuffer buffer = ByteBuffer.allocate(1024);
								buffer.put("你好".getBytes());
								buffer.flip();
								channel.write(buffer);
							}

							channel.register(selector, SelectionKey.OP_READ);
						}
						else if(key.isReadable()){  // 就说明服务器端返回了一条数据可以读了
							channel = (SocketChannel) key.channel();

							ByteBuffer buffer = ByteBuffer.allocate(1024);
							int len = channel.read(buffer);  // 把数据写入buffer，position推进到读取的字节数数字

							if(len > 0) {
								System.out.println("[" + Thread.currentThread().getName()
										+ "]收到响应：" + new String(buffer.array(), 0, len));
								Thread.sleep(5000);
								channel.register(selector, SelectionKey.OP_WRITE);
							}
						} else if(key.isWritable()) {
							ByteBuffer buffer = ByteBuffer.allocate(1024);
							buffer.put("你好".getBytes());
							buffer.flip();

							channel = (SocketChannel) key.channel();
							channel.write(buffer);
							channel.register(selector, SelectionKey.OP_READ);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(channel != null){
					try {
						channel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				if(selector != null){
					try {
						selector.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

}
