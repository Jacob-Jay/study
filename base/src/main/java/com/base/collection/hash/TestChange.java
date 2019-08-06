package com.base.collection.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-06 9:58
 */
public class TestChange {

    private int age;
    private String name;

    public TestChange(int age, String name) {
        this.age = age;
        this.name = name;
    }


    public static void main(String[] args) {
        Set<TestChange> integers = new HashSet<>();
        TestChange t1 = new TestChange(10,"jq");
        TestChange t2 = new TestChange(10,"jq");
        integers.add(t1);
        integers.add(t2);
        System.out.println(t2.hashCode());
        t2.age = 20;
        System.out.println(t2.hashCode());
        System.out.println(integers.size());
    }
}
