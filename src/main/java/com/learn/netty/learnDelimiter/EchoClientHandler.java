package com.learn.netty.learnDelimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;

import java.util.Iterator;

/**
 * Created by dongpengfei
 * Date 16/6/1
 * Time 下午6:01
 */

public class EchoClientHandler extends ChannelHandlerAdapter {

//    private int counter;

    static final String ECHO_REQ = "Hi, pengfei.dong, Welcome to netty .$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//       for(int i=0; i< 10; i++){
//           ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
//       }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse)
        {
            HttpResponse response = (HttpResponse) msg;
            Iterator iterator =  response.headers().iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }


            System.out.println("CONTENT_ENCODING:" + response.headers().get("Content-Encoding"));
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaderNames.CONTENT_TYPE));

        }
        if(msg instanceof HttpContent)
        {
            System.out.print("what the fucking ------");
            HttpContent content = (HttpContent)msg;
            ByteBuf buf = content.content();
            System.out.println(buf.toString(io.netty.util.CharsetUtil.UTF_8));
            buf.release();
        }
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        System.out.println("1111");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        System.out.println("33333");
    }


}
