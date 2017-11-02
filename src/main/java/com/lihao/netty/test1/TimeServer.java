package com.lihao.netty.test1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;


public class TimeServer {

    public void bind(int port) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)

                    .childHandler(new ChildChannelHandler());


            //绑定端口，同步等待成功
            ChannelFuture f = b.bind(port).sync();

            //等待服务器jian'ting监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

    public static class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipline = ch.pipeline();
//            pipline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
            pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
            pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
            pipline.addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) {
        int port = 9988;

        new TimeServer().bind(port);

    }
}
