package com.byhuang;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2023/2/1 15:19
 * @description ByteBuffer集中写入
 */
public class GatheringWriting {

    public static void main(String[] args) throws IOException {
        FileChannel channel = new RandomAccessFile("G:\\testFile\\test02.txt", "rw").getChannel();
        ByteBuffer buffer1 = ByteBuffer.wrap("one".getBytes());
        ByteBuffer buffer2 = ByteBuffer.wrap("two".getBytes());
        ByteBuffer buffer3 = ByteBuffer.wrap("three".getBytes());
        channel.write(new ByteBuffer[]{buffer1, buffer2, buffer3});
    }
}
