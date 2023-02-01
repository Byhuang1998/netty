package com.byhuang;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2023/2/1 18:40
 * @description TODO
 */
public class transfer {

    public static void main(String[] args) throws IOException {
        FileChannel inputChannel = new FileInputStream("G:\\testFile\\test01.txt").getChannel();
        FileChannel outputChannel = new FileOutputStream("G:\\testFile\\test02.txt").getChannel();


        long left = inputChannel.size();
        long size = inputChannel.size();
        while (left > 0) {
            left -= inputChannel.transferTo(size - left, left, outputChannel);
        }
    }
}
