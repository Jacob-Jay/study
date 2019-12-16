package com.jq.nettyt.v003pack.v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 11:18
 */
public class LogHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InetSocketAddress socketAddress = (InetSocketAddress) ((NioSocketChannel)msg).remoteAddress();
        System.out.println("有客户端连入："+socketAddress.getHostString()+"port:"+socketAddress.getPort());
        super.channelRead(ctx, msg);
        System.out.println("ok");
    }


}
