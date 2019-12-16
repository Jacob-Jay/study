package com.jq.pojo;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 16:16
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 8188713234657577806L;

    private String name;
    private int age;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", code='" + code + '\'' +
                '}';
    }
}
