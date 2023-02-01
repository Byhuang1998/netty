package com.byhuang.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/1 20:29
 * @description 使用nio来理解阻塞模式
 * @description 服务端
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 创建服务器通道
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8088));
        ByteBuffer buffer = ByteBuffer.allocate(8);
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            System.out.println("connecting...");
            SocketChannel socketChannel = ssc.accept(); // 阻塞方法，线程停止运行
            channels.add(socketChannel);
            System.out.println("connected...");

            for (SocketChannel channel : channels) {
                System.out.println("before reading...");
                channel.read(buffer); // 阻塞方法，线程停止运行
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
                System.out.println("after reading...");
            }
        }

    }
}
