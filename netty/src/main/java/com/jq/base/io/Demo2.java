package com.jq.base.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-20 20:03
 */
public class Demo2 {
    static String path = "D:\\own\\github\\study\\netty\\src\\main\\resources\\file\\fileoutPutStream.txt";
    public static void main(String[] args) throws Exception {
//        t1();
        PrintStream out = System.out;
        out.print("s");

    }

    static void t1() throws Exception {

        int[] a = new int[26];
        Reader reader= new FileReader(path);
        int i = 0;
        while ((i = reader.read()) != -1) {
            if (i - 96 > 0) {
                a[i - 97]++;
            } else {
                a[i - 65]++;
            }
        }

        for(int j= 0;j<26; j++) {
            char c = (char) (j + 97);
            System.out.println(c+"   "+a[j]);
        }

    }
}
