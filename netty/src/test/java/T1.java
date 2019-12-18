import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.ByteBuffer;
import java.util.Random;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-26 13:35
 */
public class T1 {
    public static void main(String[] args) throws Exception{
        Random random = new Random();
        for(int i = 0;i<10;i++) {
            int i1 = random.nextInt(2);
            System.out.println(i1);
        }
    }

public void m1() {

}
    private static void t3() {
        byte[] bytes = new byte[]{1, 3, 6, 5, 6};
        ByteBuffer wrap = ByteBuffer.wrap(bytes, 2, 3);
        System.out.println(wrap.order());
        wrap.putChar('v');
        wrap.putChar('v');
    }

    private static void t2() {
        byte[] bytes = new byte[]{1, 3, 6, 5, 6};
        ByteBuffer wrap = ByteBuffer.wrap(bytes, 2, 3);
        ByteBuffer slice = wrap.slice();
        byte b = slice.get();

    }

    private static void t1( ) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println(byteBuffer.remaining());
        System.out.println(byteBuffer.arrayOffset());
        System.out.println(byteBuffer.isReadOnly());
        System.out.println(byteBuffer.isDirect());
    }
}
