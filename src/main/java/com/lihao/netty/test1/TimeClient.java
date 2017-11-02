package com.lihao.netty.test1;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class TimeClient {
    public void connect(int port, String host) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
//                .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipline = ch.pipeline();
                            //换行分割
                            pipline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
                            pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            pipline.addLast(new TimeClientHandler());
                        }
                    });

            //发起异步连接操作

            ChannelFuture f = b.connect(host, port).sync();
//            f.channel().writeAndFlush("test");
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 9988;
        for(int i=0;i<10;i++) {
            System.out.println(i);
            new TimeClient().connect(port, "127.0.0.1");

        }

    }
}
