package com.chatroom.server.controller;

import com.chatroom.server.core.ChatServer;
import com.chatroom.server.DBHelper;
import com.chatroom.server.model.MessageBean;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.MessageRepositoty;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/30 0030.
 */
public class MessageController extends AbstractController{
    MessageRepositoty messageRepositoty;
    public MessageController(ChatServer server,DBHelper dbHelper) {
        super(server,dbHelper);
        messageRepositoty = new MessageRepositoty(dbHelper);
    }

    @Override
    public ProtocolResult handleRequest(String action, Map<String, String> params) {
        switch (action) {
            case "sendMessage":
                return sendMessage(params);
            case "getMessage":
                return getMessage(params);
            case "getGroupMessage":
                return getGroupMessage(params);
        }
        return null;
    }


    private ProtocolResult sendMessage(Map<String, String> param) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "message";
        result.actin = "sendMessage";

        MessageBean messageBean = new MessageBean();
        messageBean.setSender(param.get("sender"));
        messageBean.setSendername(param.get("sendername"));
        messageBean.setSendtime(param.get("sendtime"));
        messageBean.setContent(param.get("content"));
        messageBean.setReceiver(param.get("receiver"));
        messageBean.setMsgid(param.get("msgid"));

        try {
            messageRepositoty.sendMessage(messageBean);
            result.resultParams = "";
            result.resultParams = messageBean;
            server.sendBroadcast(result.resource,result.actin,messageBean);
        } catch (SQLException e) {
            e.printStackTrace();
            result.resultCode = ProtocolResult.Code_Error;
            result.resultParams = "消息发送失败";
        }

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

    private ProtocolResult getGroupMessage(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "message";
        result.actin = "getGroupMessage";
        String groupid = params.get("groupid");
        List<MessageBean> messages = messageRepositoty.getGroupMessage(groupid);
        result.resultParams = messages;
        return result;
    }
}
