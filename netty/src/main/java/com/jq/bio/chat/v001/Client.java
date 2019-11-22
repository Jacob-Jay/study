package com.jq.bio.chat.v001;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 20:24
 */
public  class Client {

    public static void main(String[] args)  throws Exception{
        Socket clinet = new Socket();
        clinet.connect(new InetSocketAddress("127.0.0.1", 8899));
        OutputStream outputStream = clinet.getOutputStream();
        InputStream inputStream = clinet.getInputStream();
        byte[]buffer = new byte[1024];
        Scanner scanner = new Scanner(System.in);
       while (true){


        outputStream.write(scanner.nextLine().getBytes("utf-8"));
           inputStream.read(buffer);
           System.out.println(new String(buffer,"utf-8"));
       }
    }

}
