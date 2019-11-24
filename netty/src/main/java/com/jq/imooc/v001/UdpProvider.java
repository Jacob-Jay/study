package com.jq.imooc.v001;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/24 10:18
 */
public class UdpProvider {


    public static void main(String[] args) throws  Exception{

        System.out.println("start...................................");
        //构建接收者
        DatagramSocket socket = new DatagramSocket(20000);

        //接受消息容器
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);

        //接收
        socket.receive(packet);
        //打印
        String ip = packet.getAddress().getHostAddress();
        int port = packet.getPort();
        int length = packet.getLength();
        String data = new String(packet.getData(), 0, length);
        System.out.println("recive message  from "+ip+"\tport:"+port+"\t message:"+data);


        //构建会送消息
        String msg = "i'm recive your message ,the length id " + length;
        byte[] bytes = msg.getBytes();
        DatagramPacket send = new DatagramPacket(bytes,bytes.length,packet.getAddress(),port);

        //回送
        socket.send(send);

        socket.close();
        System.out.println("end ...........................................");
    }
}
