package com.chatroom.server.controller;

import com.chatroom.server.DBHelper;
import com.chatroom.server.core.ChatServer;
import com.chatroom.server.model.GroupBean;
import com.chatroom.server.protocol.ProtocolResult;
import com.chatroom.server.repository.GroupRepository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class GroupController extends AbstractController{
    GroupRepository repository;
    public GroupController(ChatServer server, DBHelper dbHelper) {
        super(server, dbHelper);
        repository = new GroupRepository(dbHelper);
    }

    @Override
    public ProtocolResult handleRequest(String action, Map<String, String> params) {
        switch (action){
            case "userGroups":
                return userGroups(params);
        }
        return null;
    }

    private ProtocolResult userGroups(Map<String, String> params) {
        ProtocolResult result = new ProtocolResult();
        result.resource = "group";
        result.actin = "userGroups";
        String userid = params.get("userid");
        List<GroupBean> groups = repository.userGroups(userid);
        result.resultParams = groups;
        return result;
    }
}
