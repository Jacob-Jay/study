package com.jq.bio.chat.v001;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 20:21
 */

public class Server {


    public static void main(String[] args) throws Exception{
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1",8899));
        while (true) {
            final Socket accept = server.accept();
            new Thread(()->{
                InputStream inputStream = null;
                OutputStream outputStream = null;
                try {
                    inputStream = accept.getInputStream();
                    outputStream = accept.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[]buffer = new byte[1024];
                while (true) {
                    try {
                        inputStream.read(buffer);
                        System.out.println(new String(buffer,"utf-8"));
                        String anser = "i get your message:    "+accept.getLocalPort();
                        outputStream.write(anser.getBytes("utf-8"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }



}
