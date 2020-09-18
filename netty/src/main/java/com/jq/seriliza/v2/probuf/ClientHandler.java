package com.jq.seriliza.v2.probuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2020-09-11 11:42
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i= 0;i<10;i++){
            ctx.channel().writeAndFlush(
                    buildData(i)
                      );
        }
    }

    private SubscribeReqProto.SubscribeReq buildData(int idex) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(idex);
        builder.setUserName("user" + idex);
        builder.setProductName("书本");
        builder.setPhoneNum("12323");
        builder.setAddress("北京");
        return builder.build();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);

        SubscribeRepProto.SubscribeRep req = (SubscribeRepProto.SubscribeRep) msg;
        String result = req.getOrderNum() + "  " + req.getCode() + "  " + req.getDesc();
        System.out.println(result);
    }
}
