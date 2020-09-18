package com.jq.seriliza.v2.probuf;

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
 * @since 2020-09-11 11:42
 */
public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup woker = new NioEventLoopGroup();

        try {
            Bootstrap client = new Bootstrap();
            client.group(woker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ProtobufVarint32FrameDecoder());
                            pipeline.addLast(new ProtobufDecoder( SubscribeRepProto.SubscribeRep.getDefaultInstance()));


                            pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new ClientHandler());
                        }
                    });
            ChannelFuture sync = client.connect(Constant.HOST,Constant.PORT).sync();
            ChannelFuture sync1 = sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            woker.shutdownGracefully();
        }
    }
}
