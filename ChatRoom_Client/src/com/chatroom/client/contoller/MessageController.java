package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.MessageBean;
import com.chatroom.client.model.UserBean;
import com.chatroom.client.protocol.ProtocolResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class MessageController extends AbstractController{

    public MessageController(ChatClient client) {
        super(client);
    }

    @Override
    public void handleResponse(ProtocolResult result) {
        switch (result.actin){
            case "sendMessage":
                onSendMessage(result);
                break;
            case "getMessage":
                onGetMessage(result);
                break;
        }
    }

    private void onGetMessage(ProtocolResult result) {
        Type type = new TypeToken<List<MessageBean>>() {}.getType();
        List<MessageBean> messages = new Gson().fromJson(result.resultParams,type);
        client.jChatRoomView.setMessages(messages);

    }

    private void onSendMessage(ProtocolResult result) {
        System.out.println("onSendMessage");
    }


    public void sendMessage(){

    }

    public void getMessage(UserBean me,UserBean friend) {
        Map<String,String> param = new HashMap<>();
        param.put("userid",me.getUserid());
        param.put("friendid",friend.getUserid());
        client.doPost("message","getMessage",param);
    }
}
