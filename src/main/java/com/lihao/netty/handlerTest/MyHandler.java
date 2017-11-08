package com.lihao.netty.handlerTest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

/**
 * Created by lihao on 17/11/8.
 */
public class MyHandler extends SimpleChannelInboundHandler<Object> {

    String line = System.lineSeparator();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server: "+msg);
//        ctx.channel().writeAndFlush(LocalDateTime.now()+"    \r\n");

        ctx.channel().writeAndFlush(Unpooled.copiedBuffer((LocalDateTime.now().toString()).getBytes()));
        ctx.channel().writeAndFlush(line);

        System.out.println("server 写结束");



    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete == ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
