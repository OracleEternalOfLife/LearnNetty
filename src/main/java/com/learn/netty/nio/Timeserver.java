package com.learn.netty.nio;

import com.learn.netty.nio.Thread.MultiplexerTimerServer;

/**
 * Created by dongpengfei
 * Date 16/5/31
 * Time 下午3:25
 */

public class Timeserver {
    public static void main(String[] args){
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }

        MultiplexerTimerServer timerServer = new MultiplexerTimerServer(port);
        new Thread(timerServer, "NIO-MultiplexerTimerServer-001").start();
    }
}
