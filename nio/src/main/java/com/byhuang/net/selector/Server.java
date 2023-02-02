package com.byhuang.net.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/1 22:58
 * @description 用Selector管理多个Channel
 */
public class Server {

    public static void main(String[] args) throws IOException {
        // 1. 创建selector，管理多个channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        // 2. 建立selector和channel的联系（注册）
        // SelectionKey就是将来事件发生后，通过它可以知道事件和哪个channel的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        // key 只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        System.out.println("sscKey: " + sscKey);

        ssc.bind(new InetSocketAddress(8088));

        while (true) {
            // select方法：没有事件发生时，线程阻塞；有事件，线程才恢复运行
            // select 在事件未处理时，它不会阻塞，事件发生后要么处理，要么取消，不可置之不理
            selector.select();
            // 4. 处理事件，selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                System.out.println("key: " + key);

                if (key.isAcceptable()) {

                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    System.out.println("channel: " + channel);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    // key.cancel();
                } else if (!key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(8);
                    channel.read(buffer);
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        System.out.print(buffer.get());
                    }
                }


            }

        }

    }
}
