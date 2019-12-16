package com.jq.client.v2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 10:39
 */
public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(worker)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer(ClientHandler.SEP.getBytes())));
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    })
                    .channel(NioSocketChannel.class);
            ChannelFuture future = client.connect("127.0.0.1", 8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }

    }
}
