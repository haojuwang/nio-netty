package com.lihao.netty.test1;

import com.lihao.netty.decode.CallBackData;
import com.lihao.netty.decode.UserInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.List;

public class TimeServerHandlerMesgPack extends SimpleChannelInboundHandler<CallBackData> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CallBackData msg) throws Exception {

        if(msg.getType() == 1) {
            System.out.println("用户  server receive [ "+msg.getUserInfo()+" ]");
        } else if(msg.getType() == 2) {
            System.out.println("学生  server receive [ "+msg.getStudent()+" ]");
        }


        UserInfo userInfo =  new UserInfo();
        userInfo.setUserId(1000002);
        userInfo.setUserName("李浩");


        CallBackData callBackData =  new CallBackData();
        callBackData.setUserInfo(userInfo);
        callBackData.setType(1);


        ctx.channel().writeAndFlush(callBackData);

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
