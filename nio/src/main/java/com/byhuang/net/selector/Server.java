package com.byhuang.net.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
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
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                channel.accept();
                System.out.println("channel: " + channel);
                // key.cancel();
            }

        }

    }
}
