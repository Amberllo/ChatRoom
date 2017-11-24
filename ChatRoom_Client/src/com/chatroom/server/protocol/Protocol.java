package com.chatroom.server.protocol;

import com.chatroom.server.controller.AbstractController;
import com.chatroom.server.controller.UserController;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class Protocol {

    String resource;
    String action;
    Map<String,String> params;

    public static ProtocolResult doPost(String json){
        Gson gson = new Gson();
        Protocol protocol = gson.fromJson(json,Protocol.class);
        AbstractController controller = api.get(protocol.resource.toLowerCase());
        if(controller!=null){
            return controller.handleRequest(protocol.action,protocol.params);
        }else{
            return null;
        }
    }

    static Map<String,AbstractController> api = null;
    static{
        api = new HashMap<>();
        api.put("user",new UserController());
    }

}
