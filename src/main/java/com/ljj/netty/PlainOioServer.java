package com.ljj.netty;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @Author liujj
 * @Description TODO
 * @Date 2020/5/12 14:22
 */
public class PlainOioServer {

    public void serve(int port) throws IOException {
        final ServerSocket socket = new ServerSocket(port);
        for (; ; ) {
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            new Thread(() -> {
                OutputStream out;
                try {
                    out = clientSocket.getOutputStream();
                    out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));
                    out.flush();
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
