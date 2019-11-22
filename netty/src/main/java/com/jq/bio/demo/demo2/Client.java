package com.jq.bio.demo.demo2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 19:41
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
//            socket.bind(new InetSocketAddress("127.0.0.1",8899));
            socket.connect(new InetSocketAddress("127.0.0.1",8899));
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hi 服务端".getBytes("utf-8"));
            outputStream.flush();

            InputStream inputStream = socket.getInputStream();
            byte[]bytes  = new byte[1024];
            inputStream.read(bytes);
            System.out.println(new String(bytes,"utf-8"));

            outputStream.close();
            inputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
