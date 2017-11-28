package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.protocol.Protocol;
import com.chatroom.client.protocol.ProtocolResult;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

import static com.chatroom.client.protocol.ProtocolResult.Code_Success;

/**
 * Created by Administrator on 2017/11/26 0026.
 */
public class UserController extends AbstractController{
    public UserController(ChatClient client) {
        super(client);
    }

    @Override
    public void handleResponse(ProtocolResult result) {

        switch (result.actin){
            case "auth":
                onAuth(result);
                break;
        }
    }

    private void onAuth(ProtocolResult result) {
        if(result.resultCode == Code_Success){
            client.hideLoginView();
            client.showMainView();
        }else{
            JDialog dialog = new JDialog();
            dialog.setTitle("警告");
            dialog.add(new JLabel(result.errorMsg));
            dialog.setBounds(300,200,100,100);
            dialog.setVisible(true);
        }
    }

    public void auth(String username, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        client.doPost("User","auth",params);
    }
}
