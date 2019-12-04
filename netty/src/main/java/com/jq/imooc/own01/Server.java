package com.jq.imooc.own01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-25 10:00
 */
public class Server {
    private static List<Socket> clients = new ArrayList<>();

    public static void main(String[] args) {
        //启动tcp服务
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(Constant.TCP_SERVER_PORT),50);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println();
        }
        if (serverSocket == null) {
            System.out.println("tcp start fail");
            return;
        }
        new Thread( new TcpListener(serverSocket)).start();

        //启动upd监听服务，会送ip
        new Thread(new UdpListener()).start();
        //写消息
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            byte[] content = s.getBytes();
            send(content,null);
        }



    }


    public static void send(byte[] content,Socket socket) {
        for (Socket client : clients) {
            if (socket != null && client == socket) {
                continue;
            }
            try {
                OutputStream outputStream = client.getOutputStream();
                outputStream.write(content);
//                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(client+"  send fail......");
            }
        }
    }


    private static class TcpListener implements Runnable {
        private ServerSocket socket;

        public TcpListener(ServerSocket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {

            try {
                while (true) {
                    Socket accept = socket.accept();
                    System.out.println("客户端连接："+accept.getInetAddress().getHostAddress()+" port "+accept.getPort());
                    clients.add(accept);
                    new Thread(()->{
                        try {
                            while (true) {
                                InputStream inputStream = accept.getInputStream();
                                byte[] receive = new byte[1024];
                                int read = inputStream.read(receive);
                                System.out.println(new String(receive,0,read,"utf-8"));
                                send(receive,accept);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class UdpListener implements Runnable {
        @Override
        public void run() {
            try {
                DatagramSocket datagramSocket = new DatagramSocket(Constant.UDP_SERVER_PORT);
                byte[] content = new byte[1024];
                DatagramPacket packet = new DatagramPacket(content,content.length);
                while (true) {
                    datagramSocket.receive(packet);
                    String ip = packet.getAddress().getHostAddress();
                    int port = packet.getPort();
                    System.out.println("receive an search from ip:  "+ip+"port:"+port);
                    String msg = "you  can  connect me ";
                    byte[] send = msg.getBytes();
                    DatagramPacket sendPack = new DatagramPacket(send,send.length);
                    sendPack.setAddress(InetAddress.getByName(ip));
                    sendPack.setPort(port);
                    datagramSocket.send(sendPack);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
