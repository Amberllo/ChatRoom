package com.chatroom.server.core;

import com.chatroom.server.model.MessageBean;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/18 0018.
 */
public class ChatServer {

    private String host = "127.0.0.1";
    private int port = 7777;
    Map<String,ClientThread> clientThread = new HashMap<>();
    public void serverStart(){
        System.out.println("server run");
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                //阻塞，等待客户端连接
                Socket socket = serverSocket.accept();
                ClientThread thread = new ClientThread(this,socket);
                clientThread.put(System.currentTimeMillis()+"",thread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendBroadcast(String resource,String actin, Object data){
        for(ClientThread thread:clientThread.values()){
            thread.onBroadcast(resource,actin,data);
        }
    }





}
