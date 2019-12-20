package com.jq.nettyt.v005customprotocol.enDecode;

import com.jq.seriliza.pojo.Header;
import com.jq.seriliza.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 14:29
 */
public class OwnDecoder extends LengthFieldBasedFrameDecoder {
    private MarshallingDecoder marshallingDecoder ;
    public OwnDecoder() throws IOException {
        this(97213364);
    }
    public OwnDecoder(int maxLength) throws IOException {
        super(maxLength, 4,4,0,0 );
        marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame  = (ByteBuf) super.decode(ctx, in);
        NettyMessage nettyMessage = new NettyMessage();
        Header header = new Header();
        header.setErcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionId(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());
        int attachSize = frame.readInt();
        if (attachSize > 0) {
            Map<String,Object>attachment = new HashMap<>(attachSize);
            for(int i = 0;i<attachSize;i++) {
                int length = frame.readInt();
                ByteBuf byteBuf = frame.readBytes(length);
                String key = byteBuf.toString(CharsetUtil.UTF_8);
                attachment.put(key, marshallingDecoder.decode(frame));
            }
            header.setAttachment(attachment);
        }
        nettyMessage.setHeader(header);
        if (frame.isReadable()) {
            Object decode = marshallingDecoder.decode(frame);
            nettyMessage.setBody(decode);
        }



        return nettyMessage;
    }
}
