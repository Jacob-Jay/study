package com.jq.nettyt.v005customprotocol.v01;

import com.jq.nettyt.v005customprotocol.MarshallingFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 9:49
 */
public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();
        NioEventLoopGroup b = new NioEventLoopGroup();

        try {
            ServerBootstrap server = new ServerBootstrap();
            ChannelFuture sync = server.group(w, b)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(MarshallingFactory.buildDecoder());
                            pipeline.addLast(MarshallingFactory.buildEncoder());
                            pipeline.addLast(new Handler());
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
