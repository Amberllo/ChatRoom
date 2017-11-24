package com.chatroom.server.controller;

import com.chatroom.server.protocol.ProtocolResult;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserController extends AbstractController{
    @Override
    public ProtocolResult handleRequest(String action, Map<String, String> params) {
        switch (action){
            case "auth":
                return auth(params);
            case "friends":
                return friends(params);
        }
        return null;
    }

    private ProtocolResult friends(Map<String, String> params) {
        return null;
    }


    public ProtocolResult auth(Map<String, String> params){
        return null;
    }

}
