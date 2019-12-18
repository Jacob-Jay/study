package com.jq.protobuf.v02;

import com.jq.seriliza.probuf.ProtocalWrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 13:39
 */
public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup w = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            ChannelFuture future = client.group(w)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());

                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            pipeline.addLast(new ProtobufDecoder(ProtocalWrap.Message.getDefaultInstance()));
                            pipeline.addLast(new Handler());
                        }
                    }).connect("127.0.0.1", 8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            w.shutdownGracefully();
        }
    }
}
