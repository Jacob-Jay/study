package com.jq.bio.demo.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 19:09
 */
public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8899);
            Socket accept = server.accept();
            InputStream inputStream = accept.getInputStream();
            byte[]bytes = new byte[10];
            inputStream.read(bytes);
            String s = new String(bytes);
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
