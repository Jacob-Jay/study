package com.base.collection;

import java.util.Arrays;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/8/6 20:21
 */
public class ViewList {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("asd", "asda", "aaaa");
        strings.add("asd");
        List<String> strings1 = strings.subList(0, 1);
        System.out.println(strings);
        System.out.println(strings1);
        strings1.set(0, "qqq");
        System.out.println(strings);
        System.out.println(strings1);
    }
}
