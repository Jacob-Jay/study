package com.jq.nettyt.v004protbuf;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.jq.seriliza.probuf.StudentProto;
import com.jq.seriliza.probuf.UserProto;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 9:37
 */
public class DeEnUtil<T> {
    //编码
    public static byte[] ecode( GeneratedMessageV3 e) {

        return e.toByteArray();
    }

    //解码
    public static  UserProto.User decode2User(byte[]bytes) throws InvalidProtocolBufferException {
        return UserProto.User.parseFrom(bytes);
    }
    public static StudentProto.Student decode2Stu(byte[]bytes) throws InvalidProtocolBufferException {
        return StudentProto.Student.parseFrom(bytes);
    }

    public static StudentProto.Student createStu(UserProto.User user) {
        StudentProto.Student.Builder builder = StudentProto.Student.newBuilder();
        if (user == null) {
            builder.setName("jq");
            builder.setAge(20);
        } else {
            builder.setAge(user.getAge());
            builder.setName(user.getName());
        }


        builder.setCode("20");
        return builder.build();
    }

    public static UserProto.User createUser(StudentProto.Student student) {
        UserProto.User.Builder builder = UserProto.User.newBuilder();
        if (student == null) {
            builder.setAge(20);
            builder.setName("jq");
        } else {
            builder.setAge(student.getAge());
            builder.setName(student.getName());
        }
        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        StudentProto.Student stu = createStu(null);
        System.out.println(stu);
        System.out.println("======================");
        StudentProto.Student seconde = decode2Stu(ecode(stu));
        System.out.println(seconde);
        System.out.println("==============");
        System.out.println(stu.equals(seconde));
    }

}
