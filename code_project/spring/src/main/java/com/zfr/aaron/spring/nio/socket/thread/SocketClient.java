package com.zfr.aaron.spring.nio.socket.thread;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 10; i++) {
            new Thread() {

                public void run() {
                    try {
                        Socket socket = new Socket("localhost", 9000);

                        // 此处应该是会找DNS服务查找域名对应的IP地址
                        // 接下来需要跟那个ip地址上的9000端口的服务器程序进行TCP三次握手，建立连接
                        // 这个时候他就会构造一个三次握手中的第一次握手的TCP包
                        // 在这个TCP包里放入三次握手需要的数据
                        // 把这个TCP包封装在IP包里，是有对应的目标的IP地址，再封装在以太网包里
                        // 通过底层的硬件设备走以太网协议出去，路由器，人家通过IP地址查找路由表，确定下一个路由器的位置
                        // 查找下一个路由器的mac地址写入到以太网包头，走下一个子网广播出去
                        // 通过这种方式层层转发，一直到对应的服务器上去

                        // 服务器接收到三次握手的第一次握手的TCP包
                        // 就会回传第二次握手的TCP包给这个客户端的程序，客户端再次发送第三次握手的TCP包过去
                        // 三次握手成功，TCP连接建立起来了

                        InputStreamReader in = new InputStreamReader(socket.getInputStream());
                        OutputStream out = socket.getOutputStream();

                        out.write("你好".getBytes()); // 发送数据流，底层拆分为一个一个的TCP包发过去

                        char[] buf = new char[1024 * 1024];
                        int len = in.read(buf);

                        if(len != -1) {
                            String response = new String(buf, 0, len);
                            System.out.println("[" + Thread.currentThread().getName()
                                    + "]客户端接收到了响应：" + response);
                        }

                        in.close();
                        out.close();
                        socket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                };

            }.start();
        }
    }

}
