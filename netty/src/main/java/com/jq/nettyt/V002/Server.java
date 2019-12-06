package com.jq.nettyt.V002;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-04 11:37
 */
public class Server {
    public static void main(String[] args) {
        EventLoopGroup boss  = new NioEventLoopGroup();
        EventLoopGroup work  = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });
            ChannelFuture sync = server.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Future<?> future = boss.shutdownGracefully();
            Future<?> future1 = work.shutdownGracefully();
        }
    }
}
