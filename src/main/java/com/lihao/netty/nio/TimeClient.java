package com.lihao.netty.nio;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8899;

        new Thread(new TimeClientHandle("127.0.0.1",port),"client").start();
    }
}
