package com.byhuang;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2023/2/1 19:35
 * @description 文件遍历
 */
public class WalkFileTree {

    public static void main(String[] args) throws IOException {

    }
    /**
    * @author huangbingyi
    * @date: 2023/2/1 19:43
    * @description: 利用Files.walkFileTree统计文件和文件夹的数量
    */
    private static void walkFiles() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("C:\\Program Files\\Java\\jdk1.8.0_131"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("===>" + dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("file: " + file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("dirCount:" + dirCount);
        System.out.println("fileCount:" + fileCount);
    }
}
