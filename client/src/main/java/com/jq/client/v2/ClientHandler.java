package com.jq.client.v2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 10:40
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    public static final String SEP = "$_";
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        for(int i = 0;i<10;i++) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("你好服务器"+i+SEP).getBytes()));
        }
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().localAddress());
        super.channelRegistered(ctx);
    }
}
