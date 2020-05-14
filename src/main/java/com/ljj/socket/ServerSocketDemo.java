package com.ljj.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author liujj
 * @Description socketdemo
 * @Date 2020/5/11 16:44
 */
public class ServerSocketDemo {
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new ClientHandler(clientSocket));
        }
    }

    public static class ClientHandler implements Runnable {
        private Socket client;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter printWriter = new PrintWriter(client.getOutputStream());
                String tempstr;
                while ((tempstr = br.readLine()) != null) {
                    System.out.println(tempstr);
                    if (tempstr.equals("bye")) {
                        client.close();
                        break;
                    }
                    printWriter.write("您好\n");
                    printWriter.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
