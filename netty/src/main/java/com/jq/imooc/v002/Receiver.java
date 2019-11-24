package com.jq.imooc.v002;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/24 11:32
 */
public class Receiver {

    private volatile static List<Node> providers = new ArrayList<>();
    public static void main(String[] args)throws Exception {
        Search target = new Search();
        new Thread(target).start();

        TimeUnit.MILLISECONDS.sleep(1000);
//        countDownLatch.await();
        search();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        target.closeL();
        for (Node provider : providers) {
            System.out.println(provider);
        }
    }



    private static void search() throws Exception {
        DatagramSocket sendChannel = new DatagramSocket();
        String msg = MessageCreator.buildSearchMsg(MessageCreator.SEARCH_PORT);
        byte[] bytes = msg.getBytes();
        DatagramPacket send = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("255.255.255.255"), MessageCreator.PROVIDER_PORT);
        sendChannel.send(send);
        sendChannel.close();
    }

    private static class Node {
        private String sn;
        private String ip;
        private String port;

        @Override
        public String toString() {
            return "Node{" +
                    "sn='" + sn + '\'' +
                    ", ip='" + ip + '\'' +
                    ", port='" + port + '\'' +
                    '}';
        }

        public Node(String sn, String ip, String port) {
            this.sn = sn;
            this.ip = ip;
            this.port = port;
        }
    }


    public static class Search implements Runnable {


        private volatile boolean close = false;
        DatagramSocket socket;

        @Override
        public void run() {
            try {
                listenner();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private  void listenner() throws Exception {
             socket = new DatagramSocket(MessageCreator.SEARCH_PORT);
            byte[] mes = new byte[1024];
            DatagramPacket packet = new DatagramPacket(mes,1024);
            while (!close) {

                socket.receive(packet);
                String ip = packet.getAddress().getHostAddress();
                int port = packet.getPort();
                String s = new String(packet.getData(), 0, packet.getLength());
                Node node = new Node(MessageCreator.parseSn(s), ip, port+"");
                providers.add(node);
            }

        }

        public void closeL(){

            close = true;
            socket.close();
            socket = null;
        }

    }
}

