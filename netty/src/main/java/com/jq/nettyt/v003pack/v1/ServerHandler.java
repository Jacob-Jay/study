package com.jq.nettyt.v003pack.v1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-14 14:33
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int length = byteBuf.readableBytes();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        String s = new String(bytes);
        System.out.println(s);
        ReferenceCountUtil.release(msg);



//        super.channelRead(ctx, msg);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        super.channelReadComplete(ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer(("你好客户端"+System.getProperty("line.separator")).getBytes()));
    }
}
