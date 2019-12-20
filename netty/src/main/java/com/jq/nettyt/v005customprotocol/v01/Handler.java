package com.jq.nettyt.v005customprotocol.v01;

import com.jq.seriliza.marsharlling.Person;

import com.jq.seriliza.marsharlling.User;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 9:50
 */
public class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        User user = (User) msg;
        Person person = new Person(user.getName(), user.getAge(), "qqq");
        ctx.channel().writeAndFlush(person);
        ReferenceCountUtil.release(msg);
    }
}
