package com.chatroom.server.model;

import com.chatroom.server.protocol.ProtocolResult;

/**
 * Created by Administrator on 2017/12/2 0002.
 */
public class BroadcastPacket extends ProtocolResult{
    public final static String Tag = "Broadcast_";
    public void setResource(String resource){
        this.resource = Tag+resource;
    }
}
