package com.jq.imooc.v001;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019/11/24 10:19
 */
public class UdpReciver {

    public static void main(String[] args)  throws Exception{
        //构建通道
        DatagramSocket socket = new DatagramSocket();

        //发送数据
        String s = new String("hello  world");
        byte[] bytes = s.getBytes();

        //发送
        DatagramPacket send = new DatagramPacket(bytes,bytes.length);
        send.setAddress(InetAddress.getLocalHost());
        send.setPort(20000);
        socket.send(send);

        //接受回送
        byte [] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length);
        socket.receive(packet);
        //打印
        String ip = packet.getAddress().getHostAddress();
        int port = packet.getPort();
        int length = packet.getLength();
        String data = new String(packet.getData(), 0, length);
        System.out.println("recive message  from "+ip+"\tport:"+port+"\t message:"+data);

    }
}
