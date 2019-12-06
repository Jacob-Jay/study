package com.jq.nettyt.V002;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-04 11:37
 */
public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup woker = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(woker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture sync = client.connect("127.0.0.1", 8899).sync();
            ChannelFuture sync1 = sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            woker.shutdownGracefully();
        }
    }

}
