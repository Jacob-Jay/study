package com.jq.custom.enDecode;

import com.jq.seriliza.pojo.Header;
import com.jq.seriliza.pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-20 11:36
 */
public class OwnEncoder extends MessageToMessageEncoder<NettyMessage> {


    private MarshalingEncoder marshallingEncoder;

    public OwnEncoder() throws IOException {
        marshallingEncoder = new MarshalingEncoder();
    }


    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
        Header header = msg.getHeader();
        if (msg == null || header == null) {
            throw new IllegalStateException("msg is  not ok");
        }
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(header.getErcCode());
        buffer.writeInt(header.getLength());
        buffer.writeLong(header.getSessionId());
        buffer.writeByte(header.getType());
        buffer.writeByte(header.getPriority());
        Map<String, Object> attachment = header.getAttachment();
        int size = attachment.size();
        buffer.writeInt(size);


        byte[] attachKey = null;
        Object value = null;
        String key = null;
        for (Map.Entry<String, Object> entry : attachment.entrySet()) {
            key = entry.getKey();
            attachKey = key.getBytes(CharsetUtil.UTF_8);
            buffer.writeInt(attachKey.length);
            buffer.writeBytes(attachKey);
            value = entry.getValue();
            marshallingEncoder.encode(value,buffer);
        }
        attachKey = null;
        value = null;
        key = null;

        Object body = msg.getBody();
        if (body != null) {
            marshallingEncoder.encode(body, buffer);
        } else {
           // buffer.setInt(4, 100);
        }
        //给消息的length赋值
        buffer.setInt(4, buffer.readableBytes() - 8);
        out.add(buffer);
    }
}
