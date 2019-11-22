package com.jq.bio.demo.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 19:41
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8899);
            Socket accept = server.accept();
            InputStream inputStream = accept.getInputStream();
            byte[]bytes  = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes,"utf-8"));

            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("hi  客户端".getBytes("utf-8"));
            outputStream.close();inputStream.close();
            accept.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
