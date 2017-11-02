package com.lihao.netty.test1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TimeClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("client receive : "+msg);
//        ctx.channel().writeAndFlush("QUERY TIME ORDER"+"\r\n");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //第一次请求 连接
        System.out.println("client :  channelActive");
        ctx.channel().writeAndFlush("first--->"+"\r\n");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       cause.printStackTrace();
       ctx.close();
    }
}
