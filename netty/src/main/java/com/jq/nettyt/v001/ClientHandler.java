package com.jq.nettyt.v001;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/30 17:08
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf read = (ByteBuf) msg;
            System.out.println(read.toString(CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((ByteBuf) msg).release();
        }
    }

   /* @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channel.writeAndFlush(Unpooled.copiedBuffer("hello".getBytes(CharsetUtil.UTF_8)));

    }*/
}
