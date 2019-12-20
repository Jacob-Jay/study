package com.jq.custom.v01;

import com.jq.seriliza.marsharlling.User;
import com.jq.seriliza.pojo.Header;
import com.jq.seriliza.pojo.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 9:52
 */
public class Handler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User user = new User("jq",90);
        Header header = new Header();
        NettyMessage nettyMessage = new NettyMessage();
        nettyMessage.setHeader(header);
        nettyMessage.setBody(user);
        ctx.channel().writeAndFlush(nettyMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
        ReferenceCountUtil.release(msg);
    }
}
