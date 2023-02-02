package com.byhuang.net.nonblockmode;

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
 * @date 2023/2/1 22:33
 * @description 服务端-单线程非阻塞模式
 */
public class Server {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel server = ServerSocketChannel.open();
        // 设置成非阻塞模式，则accept()方法不会再阻塞
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8088));
        List<SocketChannel> channels = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        while (true) {
            System.out.println("connecting...");
            SocketChannel socketChannel = server.accept();
            if (socketChannel != null) {
                // 设置成非阻塞模式，则read()方法不会再阻塞
                socketChannel.configureBlocking(false);
                channels.add(socketChannel);
                System.out.println("connected...");
            }
            for (SocketChannel channel : channels) {
                int read = channel.read(buffer);
                if (read > 0) {
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.println((char) buffer.get());
                    }
                    buffer.clear();
                    System.out.println("after read...");
                }
            }
        }
    }
}
