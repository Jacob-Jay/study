package com.jq.imooc.own01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-25 14:04
 */
public class Client {
    private static List<ServerInfo> serverInfos = new ArrayList<>();

    public static void main(String[] args) {
        //查找服务器
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new FindServer(countDownLatch)).start();
        try {
            countDownLatch.await(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (serverInfos.size() == 0) {
            System.out.println("find server fail");
            return;
        }
        //连接服务器
        ServerInfo serverInfo = serverInfos.get(0);
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(serverInfo.getIp(),Constant.TCP_SERVER_PORT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //读取数据
        new Thread(()->{
            try {
                InputStream inputStream = socket.getInputStream();
                byte[] bytes = new byte[1024];
                while (true) {
                    int read = inputStream.read(bytes);
                    System.out.println(new String(bytes,0,read));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        //写数据
        new Thread(()->{
            try {
                Scanner scanner = new Scanner(System.in);
                OutputStream outputStream = socket.getOutputStream();
                while (true) {
                    String s = scanner.nextLine();
                    outputStream.write(s.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    private static class FindServer implements Runnable {
        private CountDownLatch findOver;

        public FindServer(CountDownLatch findOver) {
            this.findOver = findOver;
        }

        @Override
        public void run() {
            try {
                //广播消息
                DatagramSocket socket = new DatagramSocket(Constant.UPD_CLINET_PORT);
                String msg = "i will find you";
                byte[] msgBytes = msg.getBytes();
                DatagramPacket packet = new DatagramPacket(msgBytes,msgBytes.length, InetAddress.getByName("255.255.255.255"),Constant.UDP_SERVER_PORT);
                socket.send(packet);



                //接收会送
                byte[] rc = new byte[1024];

                DatagramPacket receive = new DatagramPacket(rc,rc.length);
                socket.receive(receive);
                String ip = receive.getAddress().getHostAddress();
                int port = receive.getPort();
                serverInfos.add(new ServerInfo(port, ip));
                System.out.println("i find  you");
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            findOver.countDown();

        }
    }



    private static  class  ServerInfo{
        int port;
        String ip;

        public ServerInfo(int port, String ip) {
            this.port = port;
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public String getIp() {
            return ip;
        }
    }
}
