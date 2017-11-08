package com.lihao.netty.delimiterBasedFrameDecoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoServerHandler extends SimpleChannelInboundHandler<String> {
    volatile  int  counter = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("this is "+ ++counter+ " times receiver client[ "+msg+" ]");
        if("exit".equals(msg)) {
            System.out.println("======================");

            ctx.close();
            return;
        }
        msg += "$_";
        ByteBuf echo = Unpooled.copiedBuffer(msg.getBytes());
        ctx.writeAndFlush(echo);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
