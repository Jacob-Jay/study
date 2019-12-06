package com.jq.nettyt.V002;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-06 17:29
 *
 * byteBuf
 *
 *
 *
 */
public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("ss".getBytes());
        System.out.println(byteBuf.maxWritableBytes());
        byteBuf.writeByte((byte)1 );

    }
}
