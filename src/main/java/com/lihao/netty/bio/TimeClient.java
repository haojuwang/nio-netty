package com.lihao.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by lihao on 17/10/31.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8899;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {

            for (int i = 0; i < 10000; i++) {
                socket = new Socket("127.0.0.1", port);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("query time order");
//                out.println();


                System.out.println("send order 2 server succeed.");
                String resp = in.readLine();
                System.out.println("Now is: " + i + "   " + resp);


            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
                out = null;
            }

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                socket = null;
            }
        }
    }
}
