package com.jq.bio.chat.v003;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-22 15:13
 */
public class Client {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",8899));
        InputStream inputStream = socket.getInputStream();
        new Thread(() ->{
            byte[] bytes = new byte[1024];
            while (true) {
                try {
                    int read = inputStream.read(bytes);
                    System.out.println(new String(bytes,0,read,"utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Scanner scanner = new Scanner(System.in);
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            String s = scanner.nextLine();
            outputStream.write(s.getBytes("utf-8"));

        }
    }
}
