package com.lihao.netty.decode;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;

@Message
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int userId;
    private String userName;
    private String address;
    private String desc = "样合计";



    public UserInfo buildUserName(String userName) {

        this.userName = userName;
        return this;
    }

    public UserInfo buildUserID(int userID) {
        this.userId = userID;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userId);
        buffer.flip();

        value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;

    }

    public byte[] codeMessageDecode() {
        MessagePack messagePack = new MessagePack();
        try {
            return messagePack.write(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", desc='" + desc + '\'' +
                ", userId=" + userId +
                '}';
    }

    //    public byte[] codeMessageDecode() {
//        byte[] value = this.userName.getBytes();
//
//        MessageBufferPacker messagePack = MessagePack.newDefaultBufferPacker();
//        try {
//            messagePack.packInt(value.length);
//            messagePack.packString(this.userName);
//            messagePack.packInt(this.userId);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return messagePack.toByteArray();
//
//    }
//
//    public byte[] codeMessageDecode2() {
//
//        byte[] value = null;
//        ByteOutputStream bos = new ByteOutputStream();
//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(this);
//            value = bos.getBytes();
//            MessageBufferPacker messagePack =  MessagePack.newDefaultBufferPacker();
//            messagePack.writePayload(value);
//
//            return messagePack.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//
//
//        return new byte[0];
//    }


}
