package com.jq.seriliza.v2.probuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2020-09-11 11:33
 */
public class ServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        printObj(req);
        super.channelRead(ctx, msg);
        ctx.channel().writeAndFlush(buildData());
    }

    private void printObj(SubscribeReqProto.SubscribeReq req) {
        String result = req.getSubReqId()+"  " + req.getUserName() +"  "+ req.getProductName()
                +"  "+ req.getPhoneNum() +"  "+ req.getAddress();
        System.out.println(result);
    }

    private  SubscribeRepProto.SubscribeRep buildData() {
        SubscribeRepProto.SubscribeRep.Builder builder = SubscribeRepProto.SubscribeRep.newBuilder();
        builder.setOrderNum("00001");
        builder.setCode(1);
        builder.setDesc("成功购买");

        SubscribeRepProto.SubscribeRep build = builder.build();
        return build;
    }
}
