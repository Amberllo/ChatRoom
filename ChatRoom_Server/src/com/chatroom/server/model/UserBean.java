package com.chatroom.server.model;

import java.io.Serializable;

import static com.chatroom.server.model.UserBean.UserState.Offline;

/**
 * Created by Administrator on 2017/11/19 0019.
 */
public class UserBean implements Serializable{

    private String userid;
    private String username;
    private String nickname;
    private String password;
    private UserState state = Offline;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public UserState getState() {
        return state;
    }

    public enum UserState{
        Online("在线"),Offline("离线");
        public String text;
        UserState(String text) {
            this.text = text;
        }
    }
}
