package com.lihao.netty.decode;

import com.lihao.netty.protobuf.UserInfoPb;
import org.msgpack.MessagePack;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class TestUserInfo {
    public static void main(String[] args) throws Exception {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Welcom to netty");

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();

        byte[] b = bos.toByteArray();

        System.out.println("The jdk Serializable length is: " + b.length);
        bos.close();

        System.out.println("-----------------------------------------");
        System.out.println("Thre byte array serializable length is : " + info.codeC().length);

        System.out.println("-----------------------------------------");
        System.out.println("message  Thre byte array serializable length is : " + info.codeMessageDecode().length);

        //解码
        MessagePack messagePack = new MessagePack();
        UserInfo messageObj = messagePack.read(info.codeMessageDecode(), UserInfo.class);
        System.out.println("-----------------------------------------");
        System.out.println(messageObj);

        com.lihao.netty.protobuf.UserInfo.Builder builder = com.lihao.netty.protobuf.UserInfo.newBuilder();
        builder.setUserId(100)
                .setUserName("Welcom to netty")
                .setDesc("样合计");
        int pblen = builder.build().toByteArray().length;
        System.out.println("-----------------------------------------");
        System.out.println(" protobuf  Thre byte array serializable length is : " + pblen);
        System.out.println("-----------------------------------------");
        System.out.println(builder.build().toString());



    }
}
