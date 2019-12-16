package com.jq.nettyt.v003pack.v3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolver;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 17:06
 */
public class Server {

    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();
        NioEventLoopGroup b = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            ChannelFuture sync = serverBootstrap.group(w, b)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ObjectDecoder(1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
                            pipeline.addLast(new Serverhandler());
                        }
                    }).bind(8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.shutdownGracefully();
            b.shutdownGracefully();
        }
    }
}
