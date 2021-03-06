package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.GroupBean;
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
            case "getGroupMessage":
                onGetGroupMessage(result);
                break;
        }

    }

    private void onGetGroupMessage(ProtocolResult result) {
        Type type = new TypeToken<List<MessageBean>>() {}.getType();
        List<MessageBean> messages = new Gson().fromJson(result.resultParams,type);
        client.jChatRoomGroupView.setMessages(messages);
    }

    @Override
    public void onBroadcast(ProtocolResult result) {
        switch (result.actin){
            case "sendMessage":
                onBroadcastSetMessage(result);
                break;
        }
    }

    private void onGetMessage(ProtocolResult result) {
        Type type = new TypeToken<List<MessageBean>>() {}.getType();
        List<MessageBean> messages = new Gson().fromJson(result.resultParams,type);
        client.jChatRoomView.setMessages(messages);

    }

    private void onBroadcastSetMessage(ProtocolResult result){
        MessageBean message = new Gson().fromJson(result.resultParams, MessageBean.class);
        if(client.jChatRoomView!=null){
            client.jChatRoomView.setMessage(message);
        }
        if(client.jChatRoomGroupView!=null){
            client.jChatRoomGroupView.setMessage(message);
        }

    }

    private void onSendMessage(ProtocolResult result) {
    }


    public void sendMessage(MessageBean messageBean){
        Map<String,String> param = new HashMap<>();
        param.put("sender",messageBean.getSender());
        param.put("sendername",messageBean.getSendername());
        param.put("sendtime",messageBean.getSendtime());
        param.put("content",messageBean.getContent());
        param.put("receiver",messageBean.getReceiver());
        param.put("msgid",messageBean.getMsgid());
        client.doPost("message","sendMessage",param);
    }

    public void getMessage(UserBean me,UserBean friend) {
        Map<String,String> param = new HashMap<>();
        param.put("userid",me.getUserid());
        param.put("friendid",friend.getUserid());
        client.doPost("message","getMessage",param);
    }

    public void getGroupMessage(GroupBean groupBean) {
        Map<String,String> param = new HashMap<>();
        param.put("groupid",groupBean.getGroupid());
        client.doPost("message","getGroupMessage",param);

    }
}
