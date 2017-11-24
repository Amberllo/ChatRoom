package com.chatroom.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DoMainClient {
    public static void main(String[] args){
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            cachedThreadPool.execute(new ClientThread("127.0.0.1",7777));
        }
    }

    static class ClientThread implements Runnable{
        private String host;
        private int port;
        ClientThread(String host,int port){
            this.host = host;
            this.port = port;
        }
        @Override
        public void run() {
            try {
                Socket socket = new Socket(host,port);
                OutputStream outputStream = socket.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write("1111");
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
