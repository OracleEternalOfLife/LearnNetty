package com.learn.netty.exampleCorrect;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by dongpengfei
 * Date 16/6/1
 * Time 下午2:32
 */

public class TimeServerHandler extends ChannelHandlerAdapter{

    private int counter;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String)msg;
        System.out.println("The time server receive order " + body + " ; the counter is : " + ++counter);

        String currentime = "QUERY TIME ORDER".equals(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
        currentime = currentime + System.getProperty("line.separator");
        ByteBuf resp = Unpooled.copiedBuffer(currentime.getBytes());
        ctx.writeAndFlush(resp);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
