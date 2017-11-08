package com.lihao.netty.fixedLengthFrameDecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class FixedServerHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server receive [ "+msg+" ]");

        ctx.writeAndFlush(Unpooled.copiedBuffer(("server send : "+ LocalDateTime.now().toString()+"\r\n").getBytes()));
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("-------------------->\r\n".getBytes()));

        if(msg.contains("exit")) {
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer("即将关闭客户端.....\r\n".getBytes()));
            ctx.channel().close();
        }

        System.out.println("end message");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
