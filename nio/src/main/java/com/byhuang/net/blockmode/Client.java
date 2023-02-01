package com.byhuang.net.blockmode;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/1 21:56
 * @description 客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {

        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress(8088));
        System.out.println("waiting...");
    }
}
