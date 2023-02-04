package com.byhuang.net.write;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/4 23:08
 * @description
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress(8088));
        int cnt = 0;
        while (true) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);
            cnt += socketChannel.read(byteBuffer);
            System.out.println(cnt);
        }
    }
}
