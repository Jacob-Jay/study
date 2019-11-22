package com.jq.bio.chat.V002;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-15 17:27
 */
public class Client {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8899));
        byte[]bytes = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();
        while (true) {
            String s = scanner.nextLine();
            outputStream.write(s.getBytes("utf-8"));
            inputStream.read(bytes);
            System.out.println(new String(bytes));
            outputStream.flush();
        }
    }
}
