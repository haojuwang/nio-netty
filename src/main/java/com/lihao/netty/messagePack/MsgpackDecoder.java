package com.lihao.netty.messagePack;

import com.lihao.netty.decode.CallBackData;
import com.lihao.netty.decode.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        System.out.println(" ==================MsgpackDecoder");
        final int length = msg.readableBytes();
        final byte[] array;
        array = new byte[length];
        msg.getBytes(msg.readerIndex(), array, 0, length);
        MessagePack messagePack = new MessagePack();

        CallBackData callBackData =  messagePack.read(array,CallBackData.class);
        out.add(callBackData);
    }
}
