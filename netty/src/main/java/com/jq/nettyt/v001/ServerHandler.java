package com.jq.nettyt.v001;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/30 16:43
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf read = (ByteBuf) msg;
            System.out.println(read.toString(CharsetUtil.UTF_8));

            ByteBuf byteBuf = Unpooled.copiedBuffer("i have reciee id #$".getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(byteBuf);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ((ByteBuf) msg).release();
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
