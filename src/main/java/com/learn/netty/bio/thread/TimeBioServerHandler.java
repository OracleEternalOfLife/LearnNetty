package com.learn.netty.bio.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by dongpengfei
 * Date 16/5/31
 * Time 上午10:39
 */

public class TimeBioServerHandler implements Runnable {

    private Socket socket;

    public TimeBioServerHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentime = null;
            String body = null;
            while(true){
                body = in.readLine();
                if(body == null){
                    break;
                }
                System.out.println("The time server receive order : " + body );
                currentime = "QUERY TIME ORDER".equals(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
                out.println(currentime);
            }


        }catch (IOException e) {
            e.printStackTrace();
            if(in != null)
                try{
                    in.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            if(out != null){
                out.close();
                out = null;
            }
            if(this.socket != null){
                try{
                    this.socket.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
                this.socket = null;
            }


        }
    }
}
