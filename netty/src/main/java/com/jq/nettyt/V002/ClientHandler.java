package com.jq.nettyt.V002;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-04 11:37
 */
public class ClientHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("你好服务器".getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf content = (ByteBuf) msg;
        int i = content.readableBytes();
        byte[] ct = new byte[i];
        content.readBytes(ct);
        String ms = new String(ct);
        System.out.println(ms);

        content.release();
    }
}
