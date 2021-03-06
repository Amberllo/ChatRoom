package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.protocol.ProtocolResult;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public abstract class AbstractController {
    protected   ChatClient client;
    public AbstractController(ChatClient client){
        this.client = client;
    }
    public abstract void handleResponse(ProtocolResult result);

    public abstract void onBroadcast(ProtocolResult result);
}
