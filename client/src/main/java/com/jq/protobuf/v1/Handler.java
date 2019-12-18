package com.jq.protobuf.v1;

import com.jq.protobuf.DeEnUtil;
import com.jq.seriliza.probuf.UserProto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-17 17:35
 */
public class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserProto.User user = DeEnUtil.createUser(null);
        ctx.channel().writeAndFlush(user);
    }
}
