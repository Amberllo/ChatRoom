package com.chatroom.client.protocol;

//import com.chatroom.server.controller.AbstractController;
//import com.chatroom.server.controller.UserController;
import com.chatroom.client.contoller.AbstractController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class Protocol {

    public String resource;
    public String action;
    public Map<String,String> params = new HashMap<>();

    public static Protocol doPost(String resource,String action,Map<String,String> params){
        Protocol controller = new Protocol();
        controller.resource = resource;
        controller.action = action;
        controller.params = params;
        return controller;
    }

//    static Map<String,AbstractController> apis = null;
//    static{
//        apis = new HashMap<>();
//        apis.put("User",new UserController());
//    }

}
