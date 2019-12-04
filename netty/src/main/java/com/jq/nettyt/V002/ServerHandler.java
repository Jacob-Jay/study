package com.jq.nettyt.V002;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-04 11:37
 */
public class ServerHandler extends ChannelInboundHandlerAdapter{


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf buf = (ByteBuf) msg;
            int i = buf.readableBytes();
            byte[] content = new byte[i];
            buf.readBytes(content);
            System.out.println(new String(content));

            ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hi".getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ((ByteBuf) msg).release();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("finish");
        ctx.writeAndFlush(null).addListener(ChannelFutureListener.CLOSE);
    }
}
