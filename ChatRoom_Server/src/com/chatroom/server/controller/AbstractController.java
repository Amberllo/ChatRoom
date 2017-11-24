package com.chatroom.server.controller;

import com.chatroom.server.protocol.ProtocolResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/24 0024.
 */
public abstract class AbstractController {
    public abstract ProtocolResult handleRequest(String action, Map<String, String> params);
}
