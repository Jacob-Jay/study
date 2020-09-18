package com.jq2020;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2020-09-10 14:44
 */
public class ByteBufTest {
    public static void main(String[] args) {
        PooledByteBufAllocator allocator = new PooledByteBufAllocator(true);
        ByteBuf buffer = allocator.buffer(112);
        System.out.println(buffer.retain());

        ByteBuf buffer1 = Unpooled.buffer(10);
    }
}
