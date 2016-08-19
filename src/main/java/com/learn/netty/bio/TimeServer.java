package com.learn.netty.bio;

import com.learn.netty.bio.thread.TimeBioServerHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dongpengfei
 * Date 16/5/31
 * Time 上午10:29
 */

public class TimeServer {
    public static void main(String[] args){
        int port = 8080;
        if(args != null && args.length > 0){
            try{
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                //采用默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port " + port);
            Socket socket = null;
            while(true){
                socket = server.accept();
                new Thread(new TimeBioServerHandler(socket)).start();
            }


        }catch (IOException e1){

        }
    }
}
