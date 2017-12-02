package com.chatroom.server.controller;

import com.chatroom.server.core.ChatServer;
import com.chatroom.server.DBHelper;
import com.chatroom.server.protocol.ProtocolResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public abstract class AbstractController {
    protected ChatServer server;
    protected DBHelper dbHelper;
    public AbstractController(ChatServer server, DBHelper dbHelper){
        this.dbHelper = dbHelper;
        this.server = server;
    }
    public abstract ProtocolResult handleRequest(String action, Map<String, String> params);
}
