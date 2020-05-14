package com.ljj.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Random;

/**
 * @Author liujj
 * @Description TODO
 * @Date 2020/5/11 16:55
 */
public class ClientSocketDemo {

    private static final Random random = new Random(47);

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(1234);
        socket.connect(socketAddress);
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter.write("你好\n");
        printWriter.flush();
        String response = bufferedReader.readLine();
        System.out.println("服务端响应_" + response);
        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
