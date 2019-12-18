package com.jq.nettyt.v004protbuf.v02;

import com.jq.seriliza.probuf.ProtocalWrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 13:47
 */
public class Server {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();
        NioEventLoopGroup b = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            ChannelFuture sync = server.group(w, b).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());


                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            pipeline.addLast(new ProtobufDecoder(ProtocalWrap.Message.getDefaultInstance()));
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
