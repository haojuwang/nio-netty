package com.lihao.netty.handlerTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by lihao on 17/11/8.
 */
public class HandlerTestClient  {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new HandlerTestClientHandler());

            ChannelFuture f = b.connect("127.0.0.1", 8887).sync();

//            Channel channel = f.channel();
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//            for(;;) {
//                channel.writeAndFlush(br.readLine()+"\r\n");
//            }

            f.channel().closeFuture().sync();


        } finally {
            group.shutdownGracefully();
        }

    }


    public static class HandlerTestClientHandler extends ChannelInitializer<SocketChannel>
    {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline cp = ch.pipeline();
            cp.addLast("line",new LineBasedFrameDecoder(1024));
//            cp.addLast("base643",new Base64Decoder());
            cp.addLast(new StringDecoder(CharsetUtil.UTF_8));
            cp.addLast(new StringEncoder(CharsetUtil.UTF_8));
            cp.addLast(new ClientHandler());

        }
    }
}
