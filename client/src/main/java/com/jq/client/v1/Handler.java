package com.jq.client.v1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-11 17:10
 */
public class Handler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        for(int i = 0;i<10;i++) {

//            ctx.writeAndFlush(Unpooled.copiedBuffer(("你好服务器"+i+System.getProperty("line.separator")).getBytes(CharsetUtil.UTF_8)));
//            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("你好服务器"+i).getBytes(CharsetUtil.UTF_8)));
            ctx.channel().writeAndFlush("你好服务器" + i);
        }
//        ctx.channel().close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ctx.channel().writeAndFlush()
//        super.channelRead(ctx, msg);
        try {
            ByteBuf bufs = (ByteBuf) msg;
            int i = bufs.readableBytes();
            byte[] content = new byte[i];
            bufs.readBytes(content);
            System.out.println(new String(content));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.refCnt(msg);
        }

    }
}
