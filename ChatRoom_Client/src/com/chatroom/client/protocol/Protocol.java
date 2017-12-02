package com.chatroom.client.protocol;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class Protocol {

    public String resource;
    public String action;
    public Map<String,String> params;



    public static String doPost(String resource, String action, Map<String, String> params) {
        Protocol protocol = new Protocol();
        protocol.params = params;
        protocol.action = action;
        protocol.resource = resource;
        return new Gson().toJson(protocol);
    }
}
