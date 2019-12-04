package com.jq.nettyt.custom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-03 19:06
 */
public class NettyDecoder extends LengthFieldBasedFrameDecoder {
    public NettyDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
         super.decode(ctx, in); //解决拆包粘包


        //将buf转换为message类
        return null;
    }
}
