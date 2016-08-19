package com.learn.netty.exampleCorrect;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;


/**
 * Created by dongpengfei
 * Date 16/6/1
 * Time 下午2:29
 */

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel arg0) throws Exception {
        arg0.pipeline().addLast(new LineBasedFrameDecoder(1024));
        arg0.pipeline().addLast(new StringDecoder());
        arg0.pipeline().addLast(new TimeServerHandler());
    }

}
