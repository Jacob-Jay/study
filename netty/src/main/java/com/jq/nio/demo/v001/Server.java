package com.jq.nio.demo.v001;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-22 16:03
 */
public class Server {

    private static Integer port = 8899;

    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress("127.0.0.1", port));
        server.configureBlocking(false);
        System.out.println(server.isRegistered());
        /*new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();*/
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            System.out.println("接收到请求");
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey next = iterator.next();
                iterator.remove();
                handle(next);

            }
        }
    }

    private static void handle(SelectionKey next) {
        if (next.isAcceptable()) {
            try {
                ServerSocketChannel sever = (ServerSocketChannel) next.channel();
                SocketChannel accept = sever.accept();
                accept.configureBlocking(false);
                accept.register(next.selector(), SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (next.isReadable()) {
            try {
                SocketChannel client = (SocketChannel) next.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = client.read(byteBuffer);
                System.out.println(new String(byteBuffer.array(),0,read));
                ByteBuffer wrap = ByteBuffer.wrap("recive".getBytes());
                client.write(wrap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
