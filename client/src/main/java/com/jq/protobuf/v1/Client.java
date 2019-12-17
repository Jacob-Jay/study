package com.jq.protobuf.v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-17 17:35
 */
public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup b = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();
        try {
            ChannelFuture future = client.group(b)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                        }
                    }).connect("127.0.0.1", 8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            b.shutdownGracefully();
        }
    }
}
