package com.jq.nettyt.v001;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/30 16:23
 */
public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("#$".getBytes())));
                            ch.pipeline().addLast(new FixedLengthFrameDecoder(20));
                            ch.pipeline().addLast(new ClientHandler());

                        }
                    });
            ChannelFuture connect = client.connect("127.0.0.1", 8899).sync();


            for (int i = 0; i < 100; i++) {
                connect.channel().writeAndFlush(Unpooled.copiedBuffer(("hello"+i+"#$").getBytes(CharsetUtil.UTF_8)));

            }

//            connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello".getBytes(CharsetUtil.UTF_8)));
//            connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello".getBytes(CharsetUtil.UTF_8)));
//            connect.channel().writeAndFlush(Unpooled.copiedBuffer("hello".getBytes(CharsetUtil.UTF_8)));

            connect.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
        }
    }
}
