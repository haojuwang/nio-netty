package com.lihao.netty.test1;

import com.lihao.netty.decode.UserInfo;
import com.lihao.netty.messagePack.MegpackEncoder;
import com.lihao.netty.messagePack.MsgpackDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.*;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TimeClient {
    public void connect(int port, String host) {

        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ClinetHandlerMessagePack());

            //发起异步连接操作

            ChannelFuture f = b.connect(host, port).sync();
//            Channel channel = f.channel();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in,CharsetUtil.UTF_8));
//
//            for(;;) {
////                channel.writeAndFlush(br.readLine()+"\r\n");
//                channel.writeAndFlush(br.readLine());
//            }

//            f.channel().writeAndFlush("test");
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("退出关闭--->");
            group.shutdownGracefully();
        }
    }


//    new ChannelInitializer<SocketChannel>() {
//        @Override
//        protected void initChannel(SocketChannel ch) throws Exception {
//            ChannelPipeline pipline = ch.pipeline();
//            //换行分割
////                            pipline.addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()));
//            pipline.addLast(new LineBasedFrameDecoder(1024));
//            pipline.addLast(new StringDecoder(CharsetUtil.UTF_8));
//            pipline.addLast(new StringEncoder(CharsetUtil.UTF_8));
//            pipline.addLast(new TimeClientHandler());
//        }
//    })

    /**
     * messagepack 支持
     */
    public static class ClinetHandlerMessagePack extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipline = ch.pipeline();
            pipline.addLast("framDecoder",new LengthFieldBasedFrameDecoder(300,0,4,0,4));


            pipline.addLast("msgpack decode",new MsgpackDecoder());
            //这里设置读取报文的包头长度来避免粘包
            ch.pipeline().addLast("frameEncoder",new LengthFieldPrepender(4));
            pipline.addLast("msgpack encode",new MegpackEncoder());
            pipline.addLast(new EchoClientHanderMesgPack());

        }
    }

    public static void main(String[] args) {
        int port = 9988;

        System.out.println("-----start:");
        new TimeClient().connect(port, "127.0.0.1");


    }
}
