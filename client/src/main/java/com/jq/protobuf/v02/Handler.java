package com.jq.protobuf.v02;

import com.jq.seriliza.probuf.ProtocalWrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.util.Random;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-18 13:40
 */
public class Handler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        System.out.println(msg);
        ProtocalWrap.Message   message = (ProtocalWrap.Message) msg;
        ProtocalWrap.packageType type = message.getType();
        if (type.getNumber() == ProtocalWrap.packageType.REQ_VALUE) {
            System.out.println(message.getReq().getContent());
        } else {
            System.out.println(message.getRes().getContent());

        }
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Random random = new Random();
        for(int i = 0;i<10;i++) {
            int i1 = random.nextInt(2);
            send(i1,ctx);
        }
    }

    public static void send(int index,ChannelHandlerContext ctx) {
        ProtocalWrap.Message.Builder builder = ProtocalWrap.Message.newBuilder();
        if (index == 0) {
            builder.setType(ProtocalWrap.packageType.REQ);
            builder.setReq(ProtocalWrap.Req.newBuilder().setContent("这是请求").build());
        }else{
            builder.setType(ProtocalWrap.packageType.RES);
            builder.setRes(ProtocalWrap.Res.newBuilder().setContent("这是响应").build());
        }
        ProtocalWrap.Message message = builder.build();
        ctx.channel().writeAndFlush(message);


    }
}
