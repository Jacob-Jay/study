package com.jq.seriliza.pojo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

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


    public static void main(String[] args) {
testTime();
    }

    public static void testTime() {
        Student student = new Student();
        student.setAge(20);
        student.setName("jq");
        student.setCode("2003");
        long l = System.currentTimeMillis();
        for(int i = 0;i<1000;i++) {
            getJave(student);
        }
        long l1 = System.currentTimeMillis();

        for(int i = 0;i<1000;i++) {
            ByteBuffer allocate = getByteBuffer(student);
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l1-l);
        System.out.println(l2-l1);

    }

    public static void testSize() {
        Student student = new Student();
        student.setAge(20);
        student.setName("jq");
        student.setCode("2003");

        byte[] java = getJave(student);
        System.out.println(java.length);

        ByteBuffer allocate = getByteBuffer(student);
        System.out.println(allocate.remaining());
    }

    private static  byte[] getJave(Student student) {
        byte[] bytes = null;
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(student);
            oo.flush();
            oo.close();
             bytes = bo.toByteArray();
            bo.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
        return bytes;
    }

    private static ByteBuffer getByteBuffer(Student student) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        byte[] name = student.getName().getBytes();
        byte[] code = student.getCode().getBytes();
        allocate.putInt(student.getAge()).putInt(name.length).put(name)
                .putInt(code.length).put(code);
        allocate.flip();
        return allocate;
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
