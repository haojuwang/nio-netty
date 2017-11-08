package com.lihao.netty.handlerTest;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by lihao on 17/11/8.
 */
public class ClientHandler extends SimpleChannelInboundHandler<String> {


    String line = System.lineSeparator();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("client receive :  "+msg);

        Thread.sleep(2000);

        ctx.channel().write("sssss"+line);
        ctx.channel().writeAndFlush(line);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("发送消息");
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(("第一次"+line).getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
