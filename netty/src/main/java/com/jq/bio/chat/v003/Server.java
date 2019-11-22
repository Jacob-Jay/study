package com.jq.bio.chat.v003;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-11-22 15:13
 */
public class Server {

    private static final List<ClientRecive> recives = new ArrayList<>();

    public static void main(String[] args) throws  Exception {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1",8899));
        while (true) {
            Socket accept = serverSocket.accept();
            recives.add(new ClientRecive(accept));
            new Thread(()->{
                try {
                    InputStream inputStream = accept.getInputStream();
                    byte[] bytes = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bytes);
                        String s = new String(bytes, 0, read, "utf-8");
                        for (ClientRecive recive : recives) {
                            if (recive.getSocket() == accept) {
                                continue;
                            }
                            recive.sendMsg(s);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

    private static class ClientRecive{

        private Socket socket;

        public ClientRecive(Socket socket) {
            this.socket = socket;
        }

        public Socket getSocket() {
            return socket;
        }

        public void sendMsg(String s) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(s.getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
