package com.chatroom.server.controller;

import com.chatroom.server.core.ChatServer;
import com.chatroom.server.DBHelper;
import com.chatroom.server.model.UserBean;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.UserRepository;

import java.util.Map;

import static com.chatroom.server.protocol.ProtocolResult.Code_Error;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserController extends AbstractController{
    private UserRepository userRepository;
    public UserController(ChatServer server, DBHelper dbHelper){
        super(server,dbHelper);
        userRepository = new UserRepository(dbHelper);
    }

    @Override
    public ProtocolResult handleRequest(String action, Map<String, String> params) {
        switch (action){
            case "auth":
                return auth(params);
            case "offline":
                return offline(params);
            case "online":
                return online(params);
            case "friends":
                return friends(params);
        }
        return null;
    }



    private ProtocolResult friends(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "User";
        result.actin = "friends";
        String userid = params.get("userid");
        result.resultParams = userRepository.getFriends(userid);
        return result;
    }


    private ProtocolResult auth(Map<String, String> params){

        String username = params.get("username");
        String password = params.get("password");

        ProtocolResult result = new ProtocolResult();
        result.resource = "User";
        result.actin = "auth";
        if(username == null || "".equals(username)){
            result.errorMsg = "用户名不能为空";
            result.resultCode = Code_Error;
        }else{
            UserBean userBean = userRepository.auth(username,password);
            if(userBean==null){
                result.errorMsg = "用户名或密码不正确";
                result.resultCode = Code_Error;
            }else{
                result.resultParams = userBean;
            }
        }
        return result;
    }

    private ProtocolResult online(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "User";
        result.actin = "online";
        result.resultParams = params;
        String userid = params.get("userid");
        userRepository.userState(userid,true);
//        server.sendBroadcast("User","online",result.resultParams);
        return result;
    }

    private ProtocolResult offline(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "User";
        result.actin = "offline";
        result.resultParams = params;
        String userid = params.get("userid");
        userRepository.userState(userid,false);
//        server.sendBroadcast("User","offline",result.resultParams);
        return result;
    }
}
