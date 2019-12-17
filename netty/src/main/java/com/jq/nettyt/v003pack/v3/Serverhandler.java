package com.jq.nettyt.v003pack.v3;

import com.jq.seriliza.pojo.Student;
import com.jq.seriliza.pojo.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 17:06
 */
public class Serverhandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        User user = (User) msg;
        Student student = new Student();
        student.setAge(user.getAge());
        student.setName(user.getName());
        student.setCode("2003");
        ctx.channel().writeAndFlush(student);

    }
}
