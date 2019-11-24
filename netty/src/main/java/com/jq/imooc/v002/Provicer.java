package com.jq.imooc.v002;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/24 11:26
 */
public class Provicer {

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        Listener listener = new Listener(s);

        new Thread(listener).start();
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        listener.closeL();

//        System.exit(0);

    }


    private static class Listener implements Runnable {

        private String sn ;

        public Listener(String sn) {
            this.sn = sn;
        }

        boolean close = false;
        DatagramSocket socket;
        @Override
        public void run() {
            System.out.println("start..........");
            try {
                socket = new DatagramSocket(MessageCreator.PROVIDER_PORT);

                byte[] bytes = new byte[1024];
                DatagramPacket packet = new DatagramPacket(bytes,1024);


                while (!close) {
                    try {
                        socket.receive(packet);

                        String ip = packet.getAddress().getHostAddress();
                        int port = packet.getPort();
                        String s = new String(packet.getData(), 0, packet.getLength());
                        System.out.println("from  "  +ip+ " port: "  +port+ " message:"+s);
                        if (MessageCreator.parsePort(s) == MessageCreator.SEARCH_PORT) {
                            String message = MessageCreator.buildProviderMsg(sn);
                            byte[] bytes1 = message.getBytes();
                            DatagramPacket send = new DatagramPacket(
                                    bytes1,
                                    bytes1.length,
                                    InetAddress.getLocalHost(),
                                    MessageCreator.SEARCH_PORT

                            );
                            socket.send(send);
                        }
                    } catch (IOException e) {
//                        e.printStackTrace();
                    }finally {

                    }
                }
                System.out.println("end..........");


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null) {

                    socket.close();
                }
            }


        }




        public   void closeL(){
            close = true;
            socket.close();
            socket = null;
        }
    }
}

