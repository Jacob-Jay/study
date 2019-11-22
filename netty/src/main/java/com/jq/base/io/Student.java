package com.jq.base.io;

import java.io.Serializable;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-19 19:13
 */
public class Student implements Serializable{
    private static final long serialVersionUID = -4685094148518683222L;
    private String name;
    private Integer age;
    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
