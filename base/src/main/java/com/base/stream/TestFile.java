package com.base.stream;

import java.io.File;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/10/5 14:41
 */
public class TestFile {

    public static void main(String[] args) {
        File file = new File("C:\\own\\github\\study\\document");
        if (file.isDirectory()) {
            File[] files = file.listFiles();//获取file的集合
            String[] strings = file.list(); //获取当
            for (File file1 : files) {
                if (file1.canRead()) {
                    System.out.println("ss");
                }
            }
        }

    }
}
