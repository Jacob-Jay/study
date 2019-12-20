package com.jq.custom.v01;

import com.jq.custom.enDecode.MarshallingFactory;
import com.jq.custom.enDecode.OwnDecoder;
import com.jq.custom.enDecode.OwnEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 9:52
 */
public class Clinet {
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
                            pipeline.addLast(new OwnEncoder());
                            pipeline.addLast(new OwnDecoder());
                            pipeline.addLast(new Handler());
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
