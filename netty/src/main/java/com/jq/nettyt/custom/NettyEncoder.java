package com.jq.nettyt.custom;

import com.sun.xml.internal.bind.v2.runtime.MarshallerImpl;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import javax.xml.bind.Marshaller;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-03 19:16
 */
public class NettyEncoder extends MessageToByteEncoder<NettyMessage> {
    private static byte[] ploacher = new byte[4];
    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf out) throws Exception {
        if (msg == null || msg.getHeader() == null) {
            throw new RuntimeException("no data");
        }
//        out.writeInt();


        Object body = msg.getBody();
        if (body == null) {
            out.writeInt(0);
        } else {

            encodeOwn(out, msg);
        }


        out.setIndex(4, out.readableBytes() - 8);
    }

    private void encodeOwn(ByteBuf out, NettyMessage msg) {
        int start = out.writerIndex();
        out.writeBytes(ploacher);
        ChannelBufferByteOutput output = new ChannelBufferByteOutput(out);

        //
        Marshaller marshaller = new MarshallerImpl(null, null);

    }
}
