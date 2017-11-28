package com.chatroom.server.controller;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.UserBean;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.chatroom.server.protocol.ProtocolResult.Code_Error;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserController extends AbstractController{
    private UserRepository userRepository;
    public UserController(DBHelper dbHelper){
        userRepository = new UserRepository(dbHelper);
    }

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

        String userid = params.get("userid");
        List<UserBean> friends = userRepository.getFriends(userid);
        for(UserBean friend:friends){
            System.out.println("好友："+friend.getNickname()+" 状态: "+friend.getState().text);
        }
        ProtocolResult result = new ProtocolResult();
        result.resultParams = userRepository.getFriends(userid);
        return result;
    }


    public ProtocolResult auth(Map<String, String> params){

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

}
