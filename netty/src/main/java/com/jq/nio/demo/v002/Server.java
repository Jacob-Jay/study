package com.jq.nio.demo.v002;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/30 15:01
 */
public class Server {


    private static ExecutorService executor = Executors.newFixedThreadPool(20);

    public static void main(String[] args) throws  Exception{
        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.bind(new InetSocketAddress(8899));
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);

        for (; ; ) {
            int select = selector.select();
            System.out.println("have ready,,,");
            if (select > 0) {
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        dealAccept(key);
                    } else if (key.isReadable()) {
                        dealRead(key);
                    }
                }
            }
        }

    }


    public static String getInfo(SocketChannel client) {
        try {
            InetSocketAddress remoteAddress = (InetSocketAddress) client.getRemoteAddress();
            return  remoteAddress.getHostName() + "port:  " + remoteAddress.getPort();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static void dealRead(SelectionKey key) {
      new Thread(()->{
          try {
              SocketChannel client = (SocketChannel) key.channel();
              ByteBuffer read = ByteBuffer.allocate(1024);
              int read1 = client.read(read);
              if (read1 != -1) {
                  read.flip();
//                  byte[] array = read.array();
                  byte[] readByte = new byte[read1];
                  read.get(readByte);
                  System.out.println("receive message from "+getInfo(client)+"    :"+new String(readByte));
//                  key.interestOps(key.interestOps()|SelectionKey.OP_READ);
//                  key.selector().wakeup();
              }else{
                  client.close();
              }


          } catch (IOException e) {
              e.printStackTrace();
          }
      }).start();
    }

    private static void dealAccept(final SelectionKey key) {

//        executor.execute(()->{
            try {
                ServerSocketChannel server = (ServerSocketChannel) key.channel();
                SocketChannel accept = server.accept();
                System.out.println(accept.toString());
                accept.configureBlocking(false);
                accept.register(key.selector(), SelectionKey.OP_READ);
                 System.out.println("receive a connect ip: " +getInfo(accept));
            } catch (Exception e) {
                e.printStackTrace();
            }
//        });

    }


}
