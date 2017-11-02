package com.lihao.netty.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by lihao on 17/10/31.
 */
public class TimeServer {
    public static void main(String[] args) {

        int port = 8899;

        ServerSocket server = null;

        try {
            server = new ServerSocket(port);
            System.out.println("this time server is start in port: " + port);
            Socket socket = null;
            int i =0;
            while (true) {
                socket = server.accept();
                i++;
                Thread thread =  new Thread(new TimeServerHandler(socket));
                thread.setName(i+" ");
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }
    }
}
