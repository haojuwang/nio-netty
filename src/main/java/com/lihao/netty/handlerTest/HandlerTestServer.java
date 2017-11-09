package com.lihao.netty.handlerTest;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.base64.Base64Decoder;
import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.CharsetUtil;

import java.nio.channels.Pipe;

/**
 * Created by lihao on 17/11/8.
 */
public class HandlerTestServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline cp = ch.pipeline();

                            // in 1 2 3 4 5    out 5 4 3 2 1
                            cp.addLast("1", new LineBasedFrameDecoder(1024));
//                           cp.addLast(new Base64Encoder());

//                            cp.addLast("base643",new Base64Encoder());

//                            cp.addLast("4", new JsonObjectDecoder());
                            cp.addLast("2", new StringDecoder(CharsetUtil.UTF_8));
                            cp.addLast("3", new StringEncoder(CharsetUtil.UTF_8));
                            cp.addLast("4", new Base64Encoder());
                            cp.addLast("6",new LoggingHandler());

                            cp.addLast("5", new MyHandler());

//                            cp.addLast("base64",new Base64Decoder());

//                            cp.addLast("base643",new Base64Encoder());
                        }
                    });

            ChannelFuture cf = serverBootstrap.bind(8887).sync();
            cf.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }
}
