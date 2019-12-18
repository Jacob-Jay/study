package com.jq.nettyt.v004protbuf.v02;

import com.jq.seriliza.probuf.ProtocalWrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 13:50
 */
public class Handler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ProtocalWrap.Message   message = (ProtocalWrap.Message) msg;
        ProtocalWrap.packageType type = message.getType();
        if (type.getNumber() == ProtocalWrap.packageType.REQ_VALUE) {
            System.out.println(message.getReq().getContent());
        } else {
            System.out.println(message.getRes().getContent());

        }
//        System.out.println(msg);
        ctx.channel().writeAndFlush(msg);
        ReferenceCountUtil.release(msg);
    }
}
