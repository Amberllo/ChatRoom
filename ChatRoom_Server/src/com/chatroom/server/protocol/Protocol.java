package com.chatroom.server.protocol;

import com.chatroom.server.controller.GroupController;
import com.chatroom.server.core.ChatServer;
import com.chatroom.server.DBHelper;
import com.chatroom.server.controller.AbstractController;
import com.chatroom.server.controller.MessageController;
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
    Map<String,AbstractController> api = new HashMap<>();
    DBHelper dbHelper;
    public Protocol(ChatServer server){
        dbHelper = new DBHelper();
        api.put("user",new UserController(server,dbHelper));
        api.put("message",new MessageController(server,dbHelper));
        api.put("group",new GroupController(server,dbHelper));
    }

    public ProtocolResult doPost(String json){
        try{
            dbHelper.openDB();
            Gson gson = new Gson();
            Protocol protocol = gson.fromJson(json,Protocol.class);
            AbstractController controller = api.get(protocol.resource.toLowerCase());
            if(controller!=null){
                return controller.handleRequest(protocol.action,protocol.params);
            }else{
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbHelper.close();
        }
        return null;
    }


}
