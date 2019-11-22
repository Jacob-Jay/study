package com.jq.bio.demo.demo1;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-14 19:09
 */
public class Client {

    public static void main(String[] args) {
//        t1();

        t2();

    }

    private static void t2() {
        InetAddress byName = null;
        try {
//            byName = InetAddress.getByName("whiteblack.online");
            InetAddress localHost = InetAddress.getLocalHost();
//            InetAddress[] allByName = InetAddress.getAllByName("wwww.baidu.com");
            byte[] address = localHost.getAddress();
            System.out.print(localHost.getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(byName.getHostAddress());
    }

    private static void t1() {
        try {
            Socket socket = new Socket("127.0.0.1", 8899);
            OutputStream outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            outputStream.write(s.getBytes());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
