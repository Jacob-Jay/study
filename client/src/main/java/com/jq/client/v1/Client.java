package com.jq.client.v1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.LineEncoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-11 17:10
 */
public class Client {
    public static void main(String[] args) {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(loopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new LineBasedFrameDecoder(1024));
//                            pipeline.addLast(new StringDecoder());
                            pipeline    .addLast(new Handler());
                            pipeline    .addLast(new LineEncoder());
//                            pipeline.addLast(new StringEncoder());
                        }
                    }).connect("localhost", 8899).sync();

            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
