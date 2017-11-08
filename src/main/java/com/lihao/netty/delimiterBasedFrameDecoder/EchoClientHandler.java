package com.lihao.netty.delimiterBasedFrameDecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHandler extends SimpleChannelInboundHandler<String> {
    private int counter ;
    static final String ECHO_REQ = "Hi , haozi .welcome to netty .$_";


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("This is "+ ++counter+ " tiems receive server : [ "+ msg +" ]");

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i=0;i<10;i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }

//        System.out.println("------->exit");
        ctx.writeAndFlush(Unpooled.copiedBuffer("exit$_".getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
