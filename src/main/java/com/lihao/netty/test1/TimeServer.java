package com.lihao.netty.test1;

import com.lihao.netty.decode.UserInfo;
import com.lihao.netty.messagePack.MegpackEncoder;
import com.lihao.netty.messagePack.MsgpackDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.LineBasedFrameDecoder;
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

                    .childHandler(new ChildChannelHandlerMessagePack());


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
            pipline.addLast(new LineBasedFrameDecoder(1024));
            pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
            pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
            pipline.addLast(new TimeServerHandler());
        }
    }


    /**
     * messagepack 支持
     */
    public static class ChildChannelHandlerMessagePack extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipline = ch.pipeline();
            pipline.addLast("framDecoder",new LengthFieldBasedFrameDecoder(65535,0,4,0,4));

            pipline.addLast("msgpack decode",new MsgpackDecoder());
            //这里设置读取报文的包头长度来避免粘包
            ch.pipeline().addLast("frameEncoder",new LengthFieldPrepender(4));
            pipline.addLast("msgpack encode",new MegpackEncoder());
            pipline.addLast("TimeServerHandlerMesgPack",new TimeServerHandlerMesgPack());

        }
    }

    public static void main(String[] args) {
        int port = 9988;

        new TimeServer().bind(port);

    }
}
