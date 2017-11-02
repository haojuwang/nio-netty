package com.lihao.netty.nio;

/**
 * Created by lihao on 17/11/1.
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8899;

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"Nio-multiplexerTimeServer-001").start();
    }
}
