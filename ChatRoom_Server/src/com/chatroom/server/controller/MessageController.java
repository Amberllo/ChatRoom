package com.chatroom.server.controller;

import com.chatroom.server.DBHelper;
import com.chatroom.server.model.MessageBean;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.MessageRepositoty;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class MessageController extends AbstractController{
    MessageRepositoty messageRepositoty;
    public MessageController(DBHelper dbHelper) {
        super();
        messageRepositoty = new MessageRepositoty(dbHelper);
    }

    @Override
    public ProtocolResult handleRequest(String action, Map<String, String> params) {
        switch (action) {
            case "sendMessage":
                return sendMessage(params);
            case "getMessage":
                return getMessage(params);
        }
        return null;
    }

    private ProtocolResult sendMessage(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "message";
        result.actin = "sendMessage";

//        String userid = params.get("userid");
//        String friendid = params.get("friendid");

        return result;
    }

    public ProtocolResult getMessage(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "message";
        result.actin = "getMessage";

        String userid = params.get("userid");
        String friendid = params.get("friendid");
        List<MessageBean> messages = messageRepositoty.getMessage(userid,friendid);
        result.resultParams = messages;
        return result;
    }
}
