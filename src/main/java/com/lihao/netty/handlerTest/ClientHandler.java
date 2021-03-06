package com.lihao.netty.handlerTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lihao.netty.decode.UserInfo;
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

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1000);
        userInfo.setUserName("李浩");
        Gson gson = new Gson();
       String json = gson.toJson(userInfo);

        ctx.channel().write(Unpooled.copiedBuffer(json.getBytes()));
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
