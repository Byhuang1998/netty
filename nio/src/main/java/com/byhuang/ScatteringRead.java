package com.byhuang;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huangbingyi
 * @version 1.0
 * @date 2023/2/1 10:31
 * @description ByteBuffer分散读
 */
public class ScatteringRead {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("G:\\testFile\\test01.txt", "rw").getChannel()) {

            ByteBuffer buffer1 = ByteBuffer.allocate(3);
            ByteBuffer buffer2 = ByteBuffer.allocate(3);
            ByteBuffer buffer3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});
            buffer1.flip();
            buffer2.flip();
            buffer3.flip();
            while (buffer1.hasRemaining()) {
                System.out.print((char)buffer1.get());
            }
            System.out.println();
            while (buffer2.hasRemaining()) {
                System.out.print((char)buffer2.get());
            }
            System.out.println();
            while (buffer3.hasRemaining()) {
                System.out.print((char)buffer3.get());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

}
