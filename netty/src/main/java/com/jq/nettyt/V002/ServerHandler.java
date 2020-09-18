package com.jq.nettyt.V002;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-04 11:37
 */
public class ServerHandler extends ChannelInboundHandlerAdapter{

    private static ExecutorService blockexe = createBusnissGrou(Runtime.getRuntime().availableProcessors() * 2);

    private static ExecutorService createBusnissGrou(int i) {
        return Executors.newFixedThreadPool(i);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);

        Future<?> submit = blockexe.submit(() -> {
            ByteBuf content=null;
            try {
                TimeUnit.MILLISECONDS.sleep(1);
                 content= (ByteBuf) msg;
                int i = content.readableBytes();
                byte[] ct = new byte[i];
                content.readBytes(ct);
                String ms = new String(ct);
                System.out.println(ms);
                ctx.channel().writeAndFlush(Unpooled.copiedBuffer("你好客户端".getBytes()));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                if (content != null) {
                    content = (ByteBuf) msg;
                }
                content.release();
            }


        });




    }


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}
