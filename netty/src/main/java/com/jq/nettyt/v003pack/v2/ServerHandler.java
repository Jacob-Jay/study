package com.jq.nettyt.v003pack.v2;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 10:36
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    public static final String SEP = "$_";
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);

        try {
            System.out.println(msg);
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("你好客户端").getBytes()));
//            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("你好客户端"+SEP).getBytes()));
        } finally {
            ReferenceCountUtil.release(msg);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("error");
//        super.exceptionCaught(ctx, cause);
    }


}
