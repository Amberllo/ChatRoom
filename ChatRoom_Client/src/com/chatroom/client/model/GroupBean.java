package com.chatroom.client.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class GroupBean implements Serializable{
    private String groupname;
    private String groupid;

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }
}
