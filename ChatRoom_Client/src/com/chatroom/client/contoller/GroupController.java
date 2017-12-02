package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.GroupBean;
import com.chatroom.client.protocol.ProtocolResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class GroupController extends AbstractController{
    public GroupController(ChatClient client) {
        super(client);
    }

    @Override
    public void handleResponse(ProtocolResult result) {
        switch (result.actin){
            case "userGroups":
                onUserGroups(result);
                break;

        }
    }

    private void onUserGroups(ProtocolResult result) {
        Type type = new TypeToken<List<GroupBean>>() {}.getType();
        List<GroupBean> groupList = new Gson().fromJson(result.resultParams,type);
        client.jMainView.setGroups(groupList);
    }

    @Override
    public void onBroadcast(ProtocolResult result) {

    }

    public void userGroups(String userid) {
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        client.doPost("group","userGroups",params);
    }
}
