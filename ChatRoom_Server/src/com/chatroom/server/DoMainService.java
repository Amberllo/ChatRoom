package com.chatroom.server;

import com.chatroom.server.core.ChatServer;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class DoMainService {
    public static void main(String[] args){
        System.out.println("service init");
        ChatServer server = new ChatServer();
        server.serverStart();
    }
}
