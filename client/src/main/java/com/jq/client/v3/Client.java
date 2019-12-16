package com.jq.client.v3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 17:05
 */
public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();
        try {
            ChannelFuture sync = client.group(w)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ObjectDecoder(1024, ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
//                            pipeline.addLast(new ObjectDecoder(1024, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));

                            pipeline.addLast(new ObjectEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    }).connect("127.0.0.1", 8899).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.shutdownGracefully();
        }
    }
}
