package com.jq.nettyt.v003pack.v2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-16 13:45
 */
public class DelimiterEncoder extends MessageToByteEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        if (msg != null) {
            out.writeBytes(msg);
            out.writeBytes(Unpooled.copiedBuffer(ServerHandler.SEP.getBytes()));
        }
    }
}
