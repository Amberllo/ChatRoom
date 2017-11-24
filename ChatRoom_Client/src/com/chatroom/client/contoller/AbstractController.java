package com.chatroom.client.contoller;

import com.chatroom.client.protocol.ProtocolResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public abstract class AbstractController {
    public abstract ProtocolResult doPost(String action, Map<String, String> params);
}
