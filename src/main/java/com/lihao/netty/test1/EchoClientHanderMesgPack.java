package com.lihao.netty.test1;

import com.lihao.netty.decode.CallBackData;
import com.lihao.netty.decode.Student;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class EchoClientHanderMesgPack extends SimpleChannelInboundHandler<CallBackData> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CallBackData msg) throws Exception {

        if (msg.getType() == 1) {
            System.out.println("用户  client receive [ " + msg.getUserInfo() + " ]");
        } else if (msg.getType() == 2) {
            System.out.println("学生 client receive [ " + msg.getStudent() + " ]");
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Student student = new Student(23, "一年级", "小名");
        CallBackData callBackData = new CallBackData();
        callBackData.setType(2);
        callBackData.setStudent(student);

        ctx.channel().writeAndFlush(callBackData);


        System.out.println("send ...");
    }
}
