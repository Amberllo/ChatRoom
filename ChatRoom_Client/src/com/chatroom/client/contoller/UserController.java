package com.chatroom.client.contoller;

import com.chatroom.client.ChatClient;
import com.chatroom.client.model.UserBean;
import com.chatroom.client.protocol.ProtocolResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            case "friends":
                onFriends(result);
                break;
            case "offline":
                break;
        }
    }

    @Override
    public void onBroadcast(ProtocolResult result) {
        switch (result.actin){
            case "offline":
                onOffline(result);
                break;
            case "online":
                onOnline(result);
                break;
        }
    }



    private void onAuth(ProtocolResult result) {
        if(result.resultCode == Code_Success){
            UserBean userBean = new Gson().fromJson(result.resultParams,UserBean.class);
            client.jLoginView.onHide();
            client.jMainView.onShow();
            client.jMainView.setUserBean(userBean);
            client.jMainView.initView();
        }else{
            JDialog dialog = new JDialog();
            dialog.setTitle("警告");
            dialog.add(new JLabel(result.errorMsg));

            //获取屏幕大小
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            dialog.setSize(150,100);
            //让窗口居中显示
            dialog.setLocation(screenSize.width/2-150/2,screenSize.height/2-150/2);
            dialog.setVisible(true);
        }
    }

    private void onFriends(ProtocolResult result){
        if(result.resultCode == Code_Success){
            Type type = new TypeToken<List<UserBean>>() {}.getType();
            List<UserBean> friends = new Gson().fromJson(result.resultParams,type);
            client.jMainView.setFriends(friends);
        }
    }

    public void auth(String username, String password) {
        Map<String,String> params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        try {
            client.connect();
            client.doPost("User","auth",params);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void friends(String userid) {
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        client.doPost("User","friends",params);
    }

    public void offline(String userid){
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        client.doPost("User","offline",params);
    }

    public void online(String userid) {
        Map<String,String> params = new HashMap<>();
        params.put("userid",userid);
        client.doPost("User","online",params);
    }

    private void onOnline(ProtocolResult result) {
//        String userid = (String)result.resultParams;
        System.out.println(result.resultParams);
    }

    private void onOffline(ProtocolResult result) {
//        String userid = (String)result.resultParams;
        System.out.println(result.resultParams);
    }


}
