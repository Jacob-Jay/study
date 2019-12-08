package com.jq.nettyt.V002;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-12-06 17:29
 *
 * byteBuf
 *
 *
 *
 * 通过slice或duplicate获取的共享试图拥有独立的index但是他们背后的数据是共享的
 * copy()复制的就是完全独立的
 *
 * 转换为         (转换之后得到的数据发生改变也会影响原数据)
 *          array   ---》hasArray确定是否有数组支持
 *
 *
 * 手动调整大小时不能超过maxCapacity
 */
public class ByteBufDemo {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);
        buffer.writeBytes("jq".getBytes());
        boolean aBoolean = buffer.getBoolean(1);
        System.out.println(aBoolean);
        System.out.println(buffer.indexOf(0, 3, (byte) 'q'));
        System.out.println(buffer.bytesBefore((byte)'q'));
    }
}
