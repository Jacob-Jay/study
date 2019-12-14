package com.jq.nettyt.v003pack.v1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-14 14:33
 */
public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup parent = new NioEventLoopGroup();
        NioEventLoopGroup child = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(parent, child)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LineBasedFrameDecoder(1024));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new ServerHandler());
                        }
                    });
            ChannelFuture sync = server.bind(8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            parent.shutdownGracefully();
            child.shutdownGracefully();
        }
    }
}
