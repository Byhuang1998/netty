package com.byhuang;

import java.nio.ByteBuffer;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2023/2/1 15:53
 * @description 解决黏包、半包问题
 */
public class ByteBufferExam {

    public static void main(String[] args) {
        /**
         * 比如传过来的信息是 Hello, world\nI'm zhangsan\nHo
         * w are you?\n
         * 信息之间是用\n分隔的，怎么给分隔开且保持信息的完整性：正确信息如下
         * Hello, world
         * I'm zhangsan
         * How are you?
         */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello, world\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());;
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            // 找到一条完整的信息
            if (source.get(i) == '\n') {
                int len = i + 1 - source.position();
                ByteBuffer temBuffer = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    temBuffer.put(source.get());
                }
                // 打印一条信息
                temBuffer.flip();
                while (temBuffer.hasRemaining()) {
                    System.out.print((char) temBuffer.get());
                }
            }

        }
        source.compact();
    }
}
