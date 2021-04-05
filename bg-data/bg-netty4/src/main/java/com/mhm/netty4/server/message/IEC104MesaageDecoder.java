package com.mhm.netty4.server.message;

import com.mhm.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;
import java.util.List;

/**
 * @author MHm
 * @date 2020-5-8 22:25
 */
public class IEC104MesaageDecoder extends ByteToMessageDecoder {
    //动态缓冲数组
    private byte[] bufBytes;
    private static byte[] HEAD_DATA = new byte[]{ 0x69 }; //协议帧起始序列 69 1个字节

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        //本包信息
        ByteBuf currBB = null;
        if (bufBytes == null) {
            currBB = msg;
        } else {
            byte[] tb = new byte[bufBytes.length + msg.readableBytes()];
            // Object src : 原数组
            // int srcPos : 从元数据的起始位置开始
            // Object dest :目标数组
            // int destPos :目标数组的开始起始位置
            // int length  :要copy的数组的长度
            System.arraycopy(bufBytes, 0, tb, 0, bufBytes.length);
            byte[] vb = new byte[msg.readableBytes()];
            msg.readBytes(vb);
            System.arraycopy(vb, 0, tb, bufBytes.length, vb.length);
            currBB = Unpooled.copiedBuffer(tb);
        }
        while (currBB.readableBytes() > 0) {
            if (!doDecode(ctx, currBB, out)) {
                break;
            }
        }
        if (currBB.readableBytes() > 0) {
            bufBytes = new byte[currBB.readableBytes()];
            currBB.readBytes(bufBytes);
        } else {
            bufBytes = null;
        }
        System.out.println(ByteUtil.toHexString(bufBytes));
        ReferenceCountUtil.release(bufBytes);
    }

    /**
     * 拆包
     *
     * @param ctx
     * @param msg
     * @param out
     * @return
     */
    private boolean doDecode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) {
        if (msg.readableBytes() < 1) {
            return false;
        }
        msg.markReaderIndex();
        byte[] header = new byte[1];
        msg.readBytes(header);
        byte[] dataLength = new byte[2]; //报文的长度
        msg.readBytes(dataLength);
        if (!Arrays.equals(header, HEAD_DATA)) {
            return false;
        }
        int len = Integer.parseInt(DatatypeConverter.printHexBinary(dataLength), 16);
        if (msg.readableBytes() < len - 4) {
            msg.resetReaderIndex();
            return false;
        }
        msg.resetReaderIndex();
        byte[] body = new byte[len];
        msg.readBytes(body);
        out.add(Unpooled.copiedBuffer(body));
        if (msg.readableBytes() > 0){
            return true;
        }
        return false;
    }
}
