package com.lihao.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static final Logger logger = Logger.getLogger(WebSocketServerHandler.class.getName());
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        logger.info("服务器收到： "+msg.text());
        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间："+ LocalDateTime.now()));


    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //每个channel 都有一个唯一的id
        System.out.println("handlerAdded: "+ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved: "+ctx.channel().id().asLongText());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }




}
