package com.jq.bio.chat.V002;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-15 17:27
 */
public class Sever {
     volatile static List<Socket> clinet = new ArrayList<>();
    final static ExecutorService EXECUTORS = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8899);
        while (true) {
            Socket accept = serverSocket.accept();
            clinet.add(accept);
            EXECUTORS.execute(new Handler(accept));
        }
    }

    private static class Handler implements Runnable {
        private Socket client;

        public Handler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            byte[] bytes = new byte[1024];
            while (true) {
                try {
                    InputStream inputStream = client.getInputStream();
                    inputStream.read(bytes);
                    int port = client.getPort();
                    String context = "收到" + port + "发来的消息" + new String(bytes, "utf-8");
                    for (Socket socket : clinet) {
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(context.getBytes("utf-8"));
                        outputStream.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
