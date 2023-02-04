package com.byhuang.net.write;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/4 22:50
 * @description 写入内容过多问题
 */
public class Server {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 30000000; i++) {
            sb.append('a');
        }
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8088));
        ssc.configureBlocking(false);
        Selector selector = Selector.open();

        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    while (buffer.hasRemaining()) {
                        int write = socketChannel.write(buffer);
                        System.out.println(write);
                    }
                }
            }
        }
    }
}