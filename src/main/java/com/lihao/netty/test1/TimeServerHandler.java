package com.lihao.netty.test1;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

public class TimeServerHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("server receive:  "+msg);
        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(msg) ? new Date().toString() : "BAD ORDER";

        //耗时
        for(int i=0;i<100000;i++) {

        }
        ctx.channel().writeAndFlush(currentTime+"\r\n");

        ctx.channel().close();
//        ctx.close();

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("server :  channelReadComplete");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();

        ctx.close();
    }
}
